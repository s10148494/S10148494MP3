package edu.np.ece.mapg.mp3.s10148494mp3;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_singlechoice, arrayList);
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

		if (id == R.id.action_add) {
			Intent add = new Intent(getBaseContext(), AddActivity.class);
			startActivityForResult(add, 99);
		} else if (id == R.id.action_search) {
			SparseBooleanArray selected = listView1.getCheckedItemPositions();
			int count = listView1.getCount();
			String strSearch = null;
			for (int i = 0; i <= count; i++) {
				if (selected.get(i)) {
					strSearch = arrayList.get(i);
				}
			}
			Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
			search.putExtra(SearchManager.QUERY, strSearch);
			startActivity(search);
		} else if (id == R.id.action_delet) {
			SparseBooleanArray selected = listView1.getCheckedItemPositions();
			int count = listView1.getCount();
			for (int i = count - 1; i >= 0; i--) {
				if (selected.get(i)) {
					arrayList.remove(i);
				}
			}
			selected.clear();
			adapter.notifyDataSetChanged();
			Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_SHORT)
					.show();
		} else if (id == R.id.action_map) {
			SparseBooleanArray selected = listView1.getCheckedItemPositions();
			int count = listView1.getCount();
			String strSearch = "";
			for (int i = 0; i <= count; i++) {
				if (selected.get(i)) {
					strSearch = arrayList.get(i);
				}
			}
			Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + strSearch);
			Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
			mapIntent.setPackage("com.google.android.apps.maps");
			startActivity(mapIntent);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 99) {
			if (resultCode == RESULT_OK) {
				String returnedData = data.getStringExtra("Data");
				arrayList.add(returnedData);
				adapter.notifyDataSetChanged();
				Toast.makeText(getBaseContext(), "Data Saved",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getBaseContext(), "Save Cancled",
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}
