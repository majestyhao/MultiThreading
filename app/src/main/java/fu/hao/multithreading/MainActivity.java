package fu.hao.multithreading;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mopub.mobileads.MoPubView;

public class MainActivity extends ActionBarActivity {
    private Button btn, btn_stop;
    private Thread thread;
    private MoPubView moPubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        btn_stop = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 1000; i++) {
                            try {
                                // will get stuck since only one main thread
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println(i);
                        }
                    }
                });
                thread.start();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });
        moPubView = (MoPubView) findViewById(R.id.adview);
        moPubView.setAdUnitId("874b5f624f9744d0a50cd4240ed442e0");
        moPubView.loadAd();
        //moPubView.setBannerAdListener(this);
    }

    protected void onDestroy() {
        moPubView.destroy();
        super.onDestroy();
    }

}
