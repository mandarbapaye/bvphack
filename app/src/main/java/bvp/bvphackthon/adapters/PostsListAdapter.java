package bvp.bvphackthon.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import bvp.bvphackthon.R;
import bvp.bvphackthon.models.Post;
import bvp.bvphackthon.views.ParseImageView;

public class PostsListAdapter extends ArrayAdapter<Post> {

    static class ViewHolder {
        @InjectView(R.id.ivFoodImageList)
        ParseImageView ivFoodImageList;

        @InjectView(R.id.tvMinsList)
        TextView tvMinsList;

        @InjectView(R.id.tvMilesList)
        TextView tvMilesList;

        @InjectView(R.id.tvMealsList)
        TextView tvMealsList;

        @InjectView(R.id.tvPostDescription)
        TextView tvPostDescription;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    public PostsListAdapter(Context context, List<Post> posts) {
        super(context, 0, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);

        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.post_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder.tvMealsList.setText(String.valueOf(post.getNumberOfFeeders()));
        viewHolder.tvMinsList.setText("20");
        viewHolder.tvMilesList.setText(String.valueOf(Math.random() * 10));
        viewHolder.tvPostDescription.setText(post.getDescription());

        ParseFile photoFile = post.getPhoto();
        if (photoFile != null) {
            viewHolder.ivFoodImageList.loadParseFileImageInBackground(photoFile);
        }

        return convertView;
    }

}



