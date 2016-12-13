package karu.videoview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Rating extends AppCompatActivity {
    RatingBar ratingBar;
    Button submit;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        textView = (TextView) findViewById(R.id.ratingmean);
        submit=(Button)findViewById(R.id.submit);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int with = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (with * 0.8), (int) (height * .6));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String s = String.valueOf(ratingBar.getRating());
                int i = (int) ratingBar.getRating();
                switch (i) {
                    case 1:
                        textView.setText("Ghét");
                        break;
                    case 2:
                        textView.setText("Không thích");
                        break;
                    case 3:
                        textView.setText("OK");
                        break;
                    case 4:
                        textView.setText("Thích");
                        break;
                    case 5:
                        textView.setText("Rất thích");
                        break;

                }


            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
