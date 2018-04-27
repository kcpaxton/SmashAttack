package trunine.smashattack;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {
    private TextView splashScreenText;
    private ImageView splashScreenImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashScreenText = (TextView) findViewById(R.id.splashScreenText);
        splashScreenImage = (ImageView) findViewById(R.id.splashScreenImage);

        Animation transition = AnimationUtils.loadAnimation(this, R.anim.splash_screen_transition);
        splashScreenText.startAnimation(transition);
        splashScreenImage.startAnimation(transition);

        final Intent i = new Intent(this, MainActivity.class);

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment frag = new Frag_FighterSelect();
        ft.add(R.id.MyFrameLayout, frag);
        ft.commit();

        startActivity(i);
        finish();

//        Thread timer = new Thread(){
//            public void run() {
//                try {
//                    sleep(3000);
////                    Fragment frag = new Frag_FighterSelect();
////                    ft.add(R.id.MyFrameLayout, frag);
////                    ft.commit();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                finally{
//                    startActivity(i);
//                    finish();
//                }
//            }
//        };
    }
}
