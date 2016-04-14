package m1.global.conndb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConnM1DB extends AsyncTask<Void, Void, String> {

	final String PrexiUrl = "jdbc:mysql://";
	ProgressDialog 	mProgressDialog;
    Context 		cnt;
    TextView        tw;
    String Res;
    private String Host, DBname, User, Pass;
  
 public ConnM1DB(Context context, String _Host, String _DBname, String _User, String _Pass)  {
    cnt = context;
    Host = _Host;
    DBname = _DBname;
    User = _User;
    Pass =  _Pass;
 }

 protected void onPreExecute() {
     mProgressDialog = ProgressDialog.show(cnt, "", "Please wait, getting database...");
 }
 
    
@Override
protected String doInBackground(Void... params) {
    try {
    	Res = "";
        Class.forName("com.mysql.jdbc.Driver");
        String Url = String.format("%s%s/%s", PrexiUrl,Host,DBname);
        java.sql.Connection con = DriverManager.getConnection(Url, User, Pass);
        
        if(con ==null)
        	return "Conn = null";
        
        java.sql.Statement st = con.createStatement();
        if(st ==null)
        	return "Statement = null";
        
        
        String r1 = String.format("select table_name from information_schema.tables where TABLE_SCHEMA=\'%s\'", DBname);
        
        
        java.sql.ResultSet rs = st.executeQuery(r1);
 
        if(rs ==null)
        	return "Query = null";
        
       ResultSetMetaData rsmd = rs.getMetaData();
       
       int ColNum = rsmd.getColumnCount();
       
         //list = new ArrayList<objClass>();

        while (rs.next()) {
        	
        	for(int i=1; i<=ColNum; i++) {
        		String field = rsmd.getColumnName(i);
        		
        		switch (rsmd.getColumnType(i)) {
        		
	        		case 2: 
	        		case 4:
	        		{
	        			int iVal = rs.getInt(field);
	        			break;
	         		 }
	       		   	case 3:
	       		   	case 6:
	       		   	{
	       		   		float  flVal = rs.getFloat(field);
	       		   		break;
	    		    }
	       		   	case 12:{
	       		   	String  srtlVal = rs.getString(field);
	       		   	Res += srtlVal;
	       		   	Res +=  System.getProperty ("line.separator");
	       		   		break;
	    		    }
        		}
        	}	
        	
              //MainActivity.playerList.add(new objectClass(field));
        }// while
        
    } 
    catch (SQLException e) {
       Log.e("111111", e.toString()) ;
    } 
    catch (ClassNotFoundException e) {
    	Log.e("111111", e.toString()) ;
    }
 
    return "Complete";
}
/*
 0	NULL
1	CHAR
2	NUMERIC
3	DECIMAL
4	INTEGER
5	SMALLINT
6	FLOAT
7	REAL
8	DOUBLE
12	VARCHAR
91	DATE
92	TIME */
protected void onPostExecute(String result) {
//    if (result.equals("Complete")) {
        mProgressDialog.dismiss();
//    }
        
        if(tw !=null)
        	tw.setText(Res);
}

}
