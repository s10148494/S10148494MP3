package edu.np.ece.mapg.mp3.s10148494mp3;

import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ArrayList<String> arrayList;
	ArrayAdapter<String> adapter;
	ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] strData = this.getResources().getStringArray(R.array.data);
		arrayList = new ArrayList<String>(Arrays.asList(strData));
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);		
		listView1 = (ListView) this.findViewById(R.id.listView1);
		listView1.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		 if (id == R.id.action_add){
			 Intent add = new Intent(getBaseContext(), AddActivity.class);
			 startActivityForResult(add, 99);
		}
		else if (id == R.id.action_edit){
			return true;
		}
		else if (id == R.id.action_delet){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 99){
			if(resultCode == RESULT_OK){
				String returnedData = data.getStringExtra("Data");
				arrayList.add(returnedData);
				adapter.notifyDataSetChanged();
			}
		}
	}
}
