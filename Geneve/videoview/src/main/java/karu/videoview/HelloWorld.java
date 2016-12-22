package karu.videoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
public class HelloWorld extends AppCompatActivity {
    VideoView videoView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        // khởi tạo object video
        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Video video=new Video();
                video.setRating("2");
                video.setContent("Play By Hoàng Khắc Hiếu");
                video.setTitle("Alan Walker - Alone");
                video.setDescription("Listen to ”Alone” on Spotify: http://bit.ly/AlanWalkerAlone\n" +
                        "Listen to ”Alone” via other plattforms: https://alanwalker.lnk.to/Alone\n" +
                        "\n" +
                        "Merch available at http://bit.ly/AlanWalkerMerch\n" +
                        "\n" +
                        "Facebook: http://bit.ly/AlanWalker_Facebook\n" +
                        "Instagram: http://bit.ly/AlanWalker_Instagram\n" +
                        "Twitter: http://bit.ly/AlanWalker_Twitter\n" +
                        "\n" +
                        "Produced by: Bror Bror\n" +
                        "Directors: Rikkard & Tobias Häggbom\n" +
                        "\n" +
                        "D.O.P: Andreas Johannessen & Rikkard Häggbom\n" +
                        "Production Manager: Kristine Planting-Gyllenbåga\n" +
                        "Production assistant: Jørgen Bertelsen\n" +
                        "\n" +
                        "Concept by: Lisa Hultengren, Johanna Hoffman & MER\n" +
                        "\n" +
                        "Drone crew Bergen: Fredrik Hinsch & Kjetil Fredriksen\n" +
                        "Drone operator Trolltunga: Tor Orset\n" +
                        "\n" +
                        "Additional footage: Alexander Zarate Frez\n" +
                        "\n" +
                        "Editing: Bror Bror\n" +
                        "Grading: Bror Bror\n" +
                        "VFX: Redrow\n" +
                        "\n" +
                        "Special thanks to Ulriken643 for their hospitality.\n" +
                        "\n" +
                        "Thank you to all the Walkers out there that contributed with footage and as extras in this music video. You are not alone.");
                video.setUploaded("Xuất bản 1 thg 12, 2016");
                video.setDuration("3'40s");
                video.setId("https://firebasestorage.googleapis.com/v0/b/geneve-english.appspot.com/o/L%E1%BA%BB%20Loi%20%C2%A6%20Ch%C3%A2u%20%C4%90%C4%83ng%20Khoa%20%C2%A6%20Yeah1%20Superstar%20(Official%20MV).mp4?alt=media&token=23314ec3-af4c-4fc0-b783-4fd67cad0039");

                Intent wordLayout = new Intent(getApplicationContext(), WatchVideo.class);
                wordLayout.putExtra("object",video);
                wordLayout.putExtra("state",false);
                startActivity(wordLayout);
            }
        });
    }
}
