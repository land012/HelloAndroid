package cf.hector.helloworld.mediademo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



import cf.hector.helloworld.R;
import android.graphics.ImageFormat;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
	
	private ImageButton playIb, pauseIb, stopIb;
	private MediaPlayer mp;
	
	private VideoView videoView;
	private MediaController mediaCtrl;
	
	private MediaRecorder mr;
	private ImageButton arRecorderIb, arStopIb;
	
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private Camera camera;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mediademo);
		Log.i("tag", new Throwable().getStackTrace()[0].getMethodName());
		
		/**
		 * 播放音乐
		 */
//		playmusic1();
		
		/**
		 * 简易音乐播放器
		 */
		playIb = (ImageButton)this.findViewById(R.id.play);
		pauseIb = (ImageButton)this.findViewById(R.id.pause);
		stopIb = (ImageButton)this.findViewById(R.id.stop);
		
		playIb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				play();
			}
		});
		pauseIb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pause();
			}
		});
		stopIb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stop();
			}
		});
		setup();
		
		/**
		 * 简易视频播放器
		 */
		this.getWindow().setFormat(PixelFormat.TRANSLUCENT);
		videoView = (VideoView)this.findViewById(R.id.video);
		mediaCtrl = new MediaController(this);
		playvideo();
		
		/**
		 * 简易音频录制
		 */
		arRecorderIb = (ImageButton)this.findViewById(R.id.arrecorder);
		arStopIb = (ImageButton)this.findViewById(R.id.arstop);
		arRecorderIb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				arRecorder();
			}
		});
		arStopIb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				arStop();
			}
		});
//		arSetup();
		
		/**
		 * 拍照
		 */
		surfaceView = (SurfaceView)this.findViewById(R.id.surfaceview);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				camera = Camera.open();
				try {
					camera.setPreviewDisplay(surfaceHolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				Camera.Parameters params = camera.getParameters();
				params.setPictureFormat(ImageFormat.JPEG);
				params.setPictureSize(width, height);
				camera.setParameters(params);
				camera.startPreview();
			}
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				camera.stopPreview();
				camera.release();
				camera = null;
			}
		});
//		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(stopIb.isEnabled()) {
			stop();
		}
		if(arStopIb.isEnabled()) {
			arStop();
		}
	}

	
	public void playmusic1() {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.linkedhorizon);
		Log.i("tag", "开始播放音乐！");
		mp.start();
		Log.i("tag", "结束播放音乐！");
	}
	
	/**
	 * -------------------- 简易音乐播放器 begin --------------------
	 */
	private void setup() {
		mp = MediaPlayer.create(this, R.raw.linkedhorizon);
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				stop();
			}
		});
		playIb.setEnabled(true);
		pauseIb.setEnabled(false);
		stopIb.setEnabled(false);
	}

	private void play() {
		mp.start();
		playIb.setEnabled(false);
		pauseIb.setEnabled(true);
		stopIb.setEnabled(true);
	}

	private void pause() {
		mp.pause();
		playIb.setEnabled(true);
		pauseIb.setEnabled(false);
		stopIb.setEnabled(false);
	}

	private void stop() {
		mp.stop();
		pauseIb.setEnabled(false);
		stopIb.setEnabled(false);
		
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mp.seekTo(0);
		playIb.setEnabled(true);
	}
	
	/**
	 * 简易视频播放器
	 */
	public void playvideo() {
		File videoFile = new File(Environment.getExternalStorageDirectory(), "Movies/KenBlock.mp4");
		videoView.setVideoPath(videoFile.getAbsolutePath());
		mediaCtrl.setMediaPlayer(videoView);
		videoView.setMediaController(mediaCtrl);
		videoView.requestFocus();
//		videoView.start();
	}
	
	/**
	 * -------------------- 简易音频录制器 --------------------
	 */
	public void arSetup() {
		mr = new MediaRecorder();
		mr.setAudioSource(MediaRecorder.AudioSource.MIC);
		mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		File arFile = new File(Environment.getExternalStorageDirectory(), "Music/audiorecorder1.mp3");
		mr.setOutputFile(arFile.getAbsolutePath());
		
		arRecorderIb.setEnabled(true);
		arStopIb.setEnabled(false);
	}
	
	private void arRecorder() {
		arRecorderIb.setEnabled(false);
		arStopIb.setEnabled(true);
		try {
			mr.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mr.start();
	}
	
	private void arStop() {
		arStopIb.setEnabled(false);
		mr.stop();
		mr.release();
		arRecorderIb.setEnabled(true);
	}
	
	public static class A {
		public void fn1() {
			
		}
		
		public static void fn2() {
			
		}
	}
	
	public class C {
		
	}
	
	
	/**
	 * 照相
	 */
	/**
	 * 成员 内部类
	 * @author asdf
	 *
	 */
	class SavePictureTask extends AsyncTask<byte[], String, String> {
		@Override
		protected String doInBackground(byte[]... arg0) {
			File photo = new File(Environment.getExternalStorageDirectory(), "Pictures/photo.jpg");
			if(photo.exists()) photo.delete();
			try {
				FileOutputStream fos =  new FileOutputStream(photo);
				fos.write(arg0[0]);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public void takePhoto() {
		camera.stopPreview();
		camera.takePicture(null, null, new Camera.PictureCallback() {
			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				new SavePictureTask().execute(data);
				camera.startPreview();
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_CAMERA || keyCode == KeyEvent.KEYCODE_SEARCH) {
			takePhoto();
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
}

class B {
	
}

