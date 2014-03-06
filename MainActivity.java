/*
    Used teamtreehouse.com to help create this app
 */
package com.DeekApps.crystal.deek.ball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.DeekApps.crystal.ball.R;
import com.DeekApps.crystal.deek.ball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity 
{
	public static final String TAG = MainActivity.class.getSimpleName();

	private CrystalDeekBall mCrystalBall = new CrystalDeekBall();
	private TextView mAnswerLabel;
	private ImageView mCrystalBallImage;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //assign  the Views from the layout file
        mAnswerLabel = (TextView)findViewById(R.id.textView1);
        mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
        
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
			
			@Override
			public void onShake() {
				handleNewAnswer();
				
			}
		});
        
        //Toast.makeText(this, "Yay! Our Activity was created!",Toast.LENGTH_LONG).show();

        Log.d(TAG, "We're logging from the onCreate() method!");
    }
    
    //When app is resumed
    @Override
    public void onResume()
    {
    	super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, 
        		SensorManager.SENSOR_DELAY_UI);
    }
    
    //When app is paused
    @Override
    public void onPause()
    {
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDetector);
    }

    //Animates the crystal ball
    private void animateCrystalBall()
    {
    	mCrystalBallImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage.getDrawable();
    	
    	if(ballAnimation.isRunning())
    	{
    		ballAnimation.stop();
    	}
    	ballAnimation.start();
    }

    
    //Animates the answer
    private void animateAnswer()
    {
    	AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
    	fadeInAnimation.setDuration(1500);
    	fadeInAnimation.setFillAfter(true);
    	mAnswerLabel.setAnimation(fadeInAnimation);
    }
    
    //Plays the crystal ball music
    private void playSound()
    {
    	MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
    	player.start();
    	player.setOnCompletionListener(new OnCompletionListener()
    	{
    		public void onCompletion(MediaPlayer mp)
    		{
    			mp.release();
    		}
    	});
    }
    
    //Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //This adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Displays new answers as they appear and animates them with sound
	private void handleNewAnswer() {
		String answer = mCrystalBall.getAnswer();
		
		//Update label with answer
		mAnswerLabel.setText(answer);
		
		
		animateCrystalBall();
		animateAnswer();
		playSound();
	}
    
}
