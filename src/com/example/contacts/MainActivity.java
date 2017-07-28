package com.example.contacts;

import java.util.ArrayList;
import java.util.List;

import com.example.contacts.ContactAdapter.OnBtnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView listView;
	private List<Contact> contactsList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.lv_contacts);
		//初始化ListView
		for(int i=0;i<50;i++){
			Contact contact = new Contact();
			
			contact.name = "志恒"+i;
			contact.phone = (5550+i)+"";
			contactsList.add(contact);
		}
		
		ContactAdapter contactAdapter = new ContactAdapter(contactsList);
		
		listView.setAdapter(contactAdapter);
		
		//我想给在此处给contactAdapter中每个条目的Button设置点击监听器
		contactAdapter.setOnBtnClickListener(new OnBtnClickListener(){
			@Override
			public void onCallClick(String phone){
				call(phone);
			};
			
			@Override
			public void onSendClick(String phone){

				Toast.makeText(MainActivity.this, "发送短信完成："+phone, Toast.LENGTH_SHORT).show();
			}
			
		});
		
	}

	protected void call(String phone) {
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:"+phone));
		startActivity(intent);
	}

}
