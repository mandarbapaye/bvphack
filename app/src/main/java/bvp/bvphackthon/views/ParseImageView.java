package bvp.bvphackthon.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;

public class ParseImageView extends ImageView {

    public ParseImageView(Context context) {
        super(context);
    }

    public ParseImageView(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public ParseImageView(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void loadParseFileImageInBackground(ParseFile parseImageFile) {
        if (parseImageFile != null) {
            parseImageFile.getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        setImage(data);
                    } else {
                        Log.d("debug", "There was a problem downloading the data.");
                    }
                }
            });
        } else {
            Log.d("debug", "Image File is null");
        }
    }

    public void loadParseFileImageInBackground(ParseFile parseImageFile, GetDataCallback callback) {
        if (parseImageFile != null) {
            parseImageFile.getDataInBackground(callback);
        } else {
            Log.d("debug", "Image File is null");
        }
    }

    private void setImage(byte[] data) {
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        this.setImageBitmap(bmp);
        this.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
