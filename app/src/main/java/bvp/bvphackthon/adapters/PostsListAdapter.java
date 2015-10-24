package bvp.bvphackthon.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import java.util.List;

import bvp.bvphackthon.R;
import bvp.bvphackthon.models.Post;
import bvp.bvphackthon.views.BVPParseImageView;

//public class PostsListAdapter extends ArrayAdapter<Post> {
//
//    static class ViewHolder {
//        @InjectView(R.id.ivFoodImageList)
//        ParseImageView ivFoodImageList;
//
//        @InjectView(R.id.tvMinsList)
//        TextView tvMinsList;
//
//        @InjectView(R.id.tvMilesList)
//        TextView tvMilesList;
//
//        @InjectView(R.id.tvMealsList)
//        TextView tvMealsList;
//
//        @InjectView(R.id.tvPostDescription)
//        TextView tvPostDescription;
//
//        public ViewHolder(View view) {
//            ButterKnife.inject(this, view);
//        }
//    }
//
//    public PostsListAdapter(Context context, List<Post> posts) {
//        super(context, 0, posts);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Post post = getItem(position);
//
//        final ViewHolder viewHolder;
//        if (convertView != null) {
//            viewHolder = (ViewHolder) convertView.getTag();
//        } else {
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.post_list_item, parent, false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }
//
//        viewHolder.tvMealsList.setText(String.valueOf(post.getNumberOfFeeders()));
//        viewHolder.tvMinsList.setText("20");
//        viewHolder.tvMilesList.setText(String.valueOf(Math.random() * 10));
//        viewHolder.tvPostDescription.setText(post.getDescription());
//
//        ParseFile photoFile = post.getPhoto();
//        if (photoFile != null) {
//            viewHolder.ivFoodImageList.loadParseFileImageInBackground(photoFile);
//        }
//
//        return convertView;
//    }
//
//}

public class PostsListAdapter extends ArrayAdapter<Post> {

    private static class ViewHolder {
        ParseImageView ivFoodImageList;
        TextView tvMinsList;
        TextView tvMilesList;
        TextView tvMealsList;
        TextView tvPostDescription;
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
            viewHolder.tvMilesList = (TextView) convertView.findViewById(R.id.tvMilesList);
            viewHolder.tvPostDescription = (TextView) convertView.findViewById(R.id.tvPostDescription);
            convertView.setTag(viewHolder);
        }

        viewHolder.position = position;
        viewHolder.tvMealsList.setText(String.valueOf(post.getNumberOfFeeders()));
        viewHolder.tvMinsList.setText("20");
        viewHolder.tvMilesList.setText(String.valueOf("1.9"));
        viewHolder.tvPostDescription.setText(post.getDescription());

        ParseFile image = post.getPhoto();
        viewHolder.ivFoodImageList.setParseFile(image);
        viewHolder.ivFoodImageList.loadInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                // The image is loaded and displayed!
//                int oldHeight = imageView.getHeight();
//                int oldWidth = imageView.getWidth();
//                Log.v("LOG!!!!!!", "imageView height = " + oldHeight);      // DISPLAYS 90 px
//                Log.v("LOG!!!!!!", "imageView width = " + oldWidth);        // DISPLAYS 90 px
            }
        });


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



