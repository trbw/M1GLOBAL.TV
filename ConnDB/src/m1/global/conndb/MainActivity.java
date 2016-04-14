package m1.global.conndb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	
	final String Host = "5.9.152.238";
	final String DBname = "instant"; 
	final String User = "instant"; 
	final String Pass = "instant";
	
	TextView txt;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    txt = (TextView)findViewById(R.id.txt); ;
 }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
   // Inflate the menu; this adds items to the action bar if it is present.
   //getMenuInflater().inflate(R.menu.main, menu);
   return true;
}
@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
}

public void onConnect(View view)  {
	txt.setText("");
    //new ConnM1DB(this, Host, DBname, User, Pass).execute(); 

	ConnM1DB conn =new ConnM1DB(this, Host, DBname, User, Pass);
	conn.tw = txt;
	conn.execute(); 

}

}
