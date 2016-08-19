package com.cpfei.mediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * 播放本地视频需要增加相应的权限
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
 * 播放网络视频
 * @author cpfei
 *
 */

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void onClick(View v) {
		Toast.makeText(MainActivity.this, "开始播放", Toast.LENGTH_SHORT).show();
		Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Test_Movie.mp4");
		// 调用系统自带的播放器
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "video/mp4");
		startActivity(intent);
	}

	public void onClickVideo(View v) {
		
		Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Test_Movie.mp4");
		VideoView videoView = (VideoView) this.findViewById(R.id.video_view);
		videoView.setMediaController(new MediaController(this));
		videoView.setVideoURI(uri);
		videoView.start();
		videoView.requestFocus();

	}

	public void onClickMedia(View v) {

		startActivity(new Intent(this, MediaPlayerActivity.class));
	}

}
