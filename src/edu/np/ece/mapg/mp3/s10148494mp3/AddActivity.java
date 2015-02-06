package edu.np.ece.mapg.mp3.s10148494mp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddActivity extends Activity {
	
	EditText etName;
	EditText etNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		etName = (EditText) this.findViewById(R.id.etName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.action_save){
			String strName = etName.getText().toString();
			
			Intent main = new Intent(); 
			main.putExtra("Data", strName);
			
			setResult(RESULT_OK, main);
			
			finish();
		}
		else if (id == R.id.action_cancel){
			Intent main = new Intent();
			setResult(RESULT_CANCELED, main);
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		String returnedData = data.getStringExtra("Name");
		etName.setText(returnedData.toString());
	}
}
