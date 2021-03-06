package kz.abcsoft.parse.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import kz.abcsoft.parse.android.adapters.CatAdapter;
import kz.abcsoft.parse.android.models.Cat;


public class MainActivity extends AppCompatActivity{

    private ListView mListView;
    private List<ParseObject> mObject;
    private ProgressDialog mProgressDialog;
    private CatAdapter mAdapter;
    private List<Cat> mCatsList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CatExecute().execute() ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CatExecute extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Parse.com ListView");
            mProgressDialog.setMessage("Загружаем...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mCatsList = new ArrayList<Cat>() ;

            try{
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Cats") ;
                query.orderByDescending("age") ;
                mObject = query.find() ;
                for(ParseObject cats : mObject){
                    Cat cat = new Cat() ;
                    cat.setName((String)cats.get("Name"));
                    cat.setAge((String) cats.get("age"));
                    cat.setColor((String) cats.get("color"));
                    mCatsList.add(cat);
                }
            }catch (ParseException e){
                Log.e("ERROR", e.getMessage()) ;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mListView = (ListView) findViewById(R.id.listView);
            mAdapter = new CatAdapter(MainActivity.this,
                    mCatsList);

            mListView.setAdapter(mAdapter);
            mProgressDialog.dismiss();
        }
    }
}
