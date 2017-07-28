package com.example.contacts;

import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

	private List<Contact> contactsList;
	public ContactAdapter(List<Contact> contactsList) {
		this.contactsList = contactsList;
	}

	@Override
	
	public int getCount() {
		return contactsList==null?0:contactsList.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Contact contact = contactsList.get(position);
		ViewHolder viewHolder = null;
		if (convertView==null) {
			
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent ,false);
			viewHolder = new ViewHolder();
			
			viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
			viewHolder.btnCall = (Button) convertView.findViewById(R.id.btn_call);
			viewHolder.btnSend = (Button) convertView.findViewById(R.id.btn_send);

			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvName.setText(contact.name);
		viewHolder.tvPhone.setText(contact.phone);
		
		viewHolder.btnCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mOnBtnClickListener!=null) {
					mOnBtnClickListener.onCallClick(contact.phone);
				}
			}
		});
		
		viewHolder.btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mOnBtnClickListener!=null) {
					mOnBtnClickListener.onSendClick(contact.phone);
				}
			}
		});
		
		return convertView;
	}
	
	@Override
	public Contact getItem(int position) {
		return contactsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	class ViewHolder{
		TextView tvName;
		TextView tvPhone ;
		Button btnCall;
		Button btnSend;
	}
	
	public interface OnBtnClickListener{
		void onCallClick(String phone);
		void onSendClick(String phone);
	}

	private OnBtnClickListener mOnBtnClickListener;
	public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
		this.mOnBtnClickListener = onBtnClickListener;
	}
	

}
