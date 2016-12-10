package karu.videoview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class WatchVideo extends AppCompatActivity {
    //init variant
    private VideoView videoView;
    private Button button, button2;
    private ToggleButton information;
    private LayoutParams params;
    private TextView title;
    private RatingBar ratingBar;
    private boolean click = true;
    private Video object = new Video();
    private int previousHeight = 0;
    private int previousWidth = 0;
    private DatabaseReference mData;
    //
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    // set full
    public void setFullscreen() {

        VideoView videoView = (VideoView) findViewById(R.id.videoview);
        LinearLayout container = (LinearLayout) findViewById(R.id.container_video);
        // ghi nhớ size ở normal screen
        previousHeight = container.getHeight();
        previousWidth = container.getWidth();
        // set container full screen
        LayoutParams params =container.getLayoutParams();
        params.height = getScreenWidth();
        params.width = getScreenHeight();
        container.setLayoutParams(params);
        // set video full screen
        LinearLayout.LayoutParams paramsvideo = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        paramsvideo.height = getScreenWidth();
        paramsvideo.width = getScreenHeight();
        paramsvideo.weight = 1.0f;
        paramsvideo.gravity = Gravity.CENTER;
        videoView.setLayoutParams(paramsvideo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    // set normal
    public void setNormalscreen() {
        VideoView videoView = (VideoView) findViewById(R.id.videoview);
        LinearLayout layout = (LinearLayout) findViewById(R.id.container_video);
        LayoutParams params = layout.getLayoutParams();
        params.height = previousHeight;
        params.width = previousWidth;
        layout.setLayoutParams(params);
        videoView.setLayoutParams(params);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);
        // set fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //set portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // set state when turn off screen
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                    videoView.pause();
                } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                    //when screen on
                }
            }
        }, intentFilter);
        //init variants
        videoView = (VideoView) findViewById(R.id.videoview);
        information = (ToggleButton) findViewById(R.id.more);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        title = (TextView) findViewById(R.id.title);
        button2 = (Button) findViewById(R.id.button3);
        mData = FirebaseDatabase.getInstance().getReference();
        //lấy đối tượng video được truyền vào module
        final Intent intent = getIntent();
        String id=intent.getExtras().getString("id");
        object = (Video) intent.getSerializableExtra("object");

        //Search in database

        // khởi tạo media player
        VideoController mediaController = new VideoController(this);
        //MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        final Uri video = Uri.parse(object.getId());
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                boolean state = intent.getExtras().getBoolean("state");
                if (state == true) {
                    videoView.seekTo(intent.getExtras().getInt("stopat"));
                }
            }
        });
        videoView.setVideoURI(video); // set link cho video
        // khi video kết thúc
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.pause();
            }
        });

        // export video title
        title.setText(object.getTitle());

        // khi ấn nút more để lấy thêm thông tin của video
        final LinearLayout container,containernext;
        container = (LinearLayout) findViewById(R.id.container_information);
        information.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // layout chứa thông tin của video
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = layoutInflater.inflate(R.layout.information, null); //
                    // init variant
                    TextView id, description, dateupload, content;
                    id = (TextView) addView.findViewById(R.id.id);
                    description = (TextView) addView.findViewById(R.id.description);
                    dateupload = (TextView) addView.findViewById(R.id.dateupload);
                    content = (TextView) addView.findViewById(R.id.content);
                    // set view
                    id.setText("id video" + object.getDuration()); // set duration
                    dateupload.setText(object.getUploaded()); // set date upload
                    description.setText(object.getDescription()); // set description
                    content.setText(object.getContent()); // set content
                    container.addView(addView);
                } else {
                    // The toggle is disabled
                    container.removeAllViews();
                }
            }
        });
        // thêm các video trong cataloge
        containernext =(LinearLayout) findViewById(R.id.container_next);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addView = layoutInflater.inflate(R.layout.next_model, null); //
        containernext.addView(addView);
        ratingBar.setRating(Float.valueOf(object.getRating()));
        ratingBar.setEnabled(false);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WatchVideo.this, Rating.class));
            }
        });


    }

    // Custom Media Controller
    public class VideoController extends MediaController {
        Context mContext;
        boolean fullscreen;
        private Button searchButton;

        public VideoController(Context context) {
            super(context);
            mContext = context;
        }

        // override Anchor để custom MediaController
        @Override
        public void setAnchorView(View view) {
            super.setAnchorView(view);
            LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View addView = layoutInflater.inflate(R.layout.fullscreenbutton, null); //

            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.RIGHT;
            addView(addView, params);
            Button searchButton = (Button) addView.findViewById(R.id.fullbutton);
            fullscreen = false;
            searchButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int orientation = mContext.getResources().getConfiguration().orientation;
                    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                        //code for portrait mode
                        setFullscreen();
                    } else {
                        //code for landscape mode
                        setNormalscreen();
                    }
                }
            });
        }

    }
}
