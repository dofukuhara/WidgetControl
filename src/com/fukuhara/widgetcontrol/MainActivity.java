package com.fukuhara.widgetcontrol;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Context contexto;

	
	private Button audioButton;
	private Button wifiButton;
	
	AudioManager audioControl;
	WifiManager wifiControl;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		contexto = getApplicationContext(); 
		
		audioButton = (Button) findViewById(R.id.button1);
		wifiButton = (Button) findViewById(R.id.button2);
		
		audioControl = (AudioManager) contexto.getSystemService(Context.AUDIO_SERVICE);
		wifiControl = (WifiManager) contexto.getSystemService(Context.WIFI_SERVICE);
		
		
		
		int ringMode = audioControl.getRingerMode();
		
		if (ringMode == audioControl.RINGER_MODE_NORMAL) {
			audioButton.setText(R.string.button_normal);
		} else if (ringMode == audioControl.RINGER_MODE_SILENT) {
			audioButton.setText(R.string.button_siltent);
		} else {
			audioButton.setText(R.string.button_vibrate);
		}
		
		int wifiMode = wifiControl.getWifiState();
		
		if (wifiMode == wifiControl.WIFI_STATE_DISABLED) {
			wifiButton.setText(R.string.button_wifi_off);
		} else if (wifiMode == wifiControl.WIFI_STATE_DISABLING) {
			wifiButton.setText(R.string.button_wifi_off);
		} else if (wifiMode == wifiControl.WIFI_STATE_ENABLED) {
			wifiButton.setText(R.string.button_wifi_on);
		} else if (wifiMode == wifiControl.WIFI_STATE_ENABLING) {
			wifiButton.setText(R.string.button_wifi_on);
		} else if (wifiMode == wifiControl.WIFI_STATE_UNKNOWN) {
			wifiButton.setText(R.string.button_wifi_off);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void changeRingerMode(View view) {

		int ringMode = audioControl.getRingerMode();
		
		if (ringMode == audioControl.RINGER_MODE_NORMAL) {
			audioControl.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			audioButton.setText(R.string.button_vibrate);
			
			Toast.makeText(contexto, R.string.button_normal, Toast.LENGTH_SHORT).show();
		} else if (ringMode == audioControl.RINGER_MODE_VIBRATE) {
			audioControl.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			audioButton.setText(R.string.button_siltent);
			
			Toast.makeText(contexto, R.string.button_vibrate, Toast.LENGTH_SHORT).show();
		} else {
			audioControl.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			audioButton.setText(R.string.button_normal);
			
			Toast.makeText(contexto, R.string.button_siltent, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void changeWifiMode(View view) {
		
		int wifiMode = wifiControl.getWifiState();
		
		if (wifiMode == wifiControl.WIFI_STATE_DISABLED || wifiMode == wifiControl.WIFI_STATE_DISABLING) {
			wifiControl.setWifiEnabled(true);
			wifiButton.setText(R.string.button_wifi_on);

			Toast.makeText(contexto, R.string.button_wifi_on, Toast.LENGTH_SHORT).show();
			
		} else if (wifiMode == wifiControl.WIFI_STATE_ENABLED || wifiMode == wifiControl.WIFI_STATE_ENABLING) {
			wifiControl.setWifiEnabled(false);
			wifiButton.setText(R.string.button_wifi_off);

			Toast.makeText(contexto, R.string.button_wifi_off, Toast.LENGTH_SHORT).show();
			
		} else if (wifiMode == wifiControl.WIFI_STATE_UNKNOWN) {
			
			Toast.makeText(contexto, R.string.button_wifi_unknown, Toast.LENGTH_SHORT).show();
		}
		
	}
}
