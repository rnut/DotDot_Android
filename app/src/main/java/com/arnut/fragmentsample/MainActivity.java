package com.arnut.fragmentsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;

import com.arnut.fragmentsample.Dot.FontColor;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.zip.Inflater;

import static com.arnut.fragmentsample.Dots.*;




public class MainActivity extends Activity implements OnDotsChangeListener, DotsView.OnDotsTouchListener {

    private static final int MAX_COORD_Y = 500;
    private static final int MAX_COORD_X = 500;
    private static final int Menu_CLEAR_ITEM = 1001;
    private static final int MENU_DELETE_ITEM = 10002;
    private static final int MENU_EDIT_ITEM = 1003;
    private Dots mDots = new Dots();
    private Random mGenerator = new Random();
    private ListView mListView;
    private DotsListAdapter mAdapter;
    private DotsView mDotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDots.setOnDotsChangeListener(this);
        mDotView = (DotsView) findViewById(R.id.dotView);
        mDotView.setOnDotsTouchListener(this);
        mDotView.setDataSource(new DotsView.DotsViewDataSource() {
            @Override
            public Dot getItem(int position) {
                return mDots.get(position);
            }

            @Override
            public int getCount() {
                return mDots.size();
            }
        });


        mAdapter = new DotsListAdapter(this) {
            @Override
            public int getCount() {
                return mDots.size();
            }

            @Override
            public Object getItem(int position) {
                return mDots.get(position);
            }
        };

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);

        registerForContextMenu(mListView);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(Menu.NONE, Menu_CLEAR_ITEM, Menu.NONE, R.string.clear);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case Menu_CLEAR_ITEM:
                mDots.clear();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void randomDot(View view){
        int coordX = mGenerator.nextInt(mDotView.getWidth());
        int coordY = mGenerator.nextInt(mDotView.getHeight());
        int fontsize = mGenerator.nextInt(20)+5;
        Log.d("random",String.valueOf(fontsize));
        FontColor c = FontColor.getByOrder(mGenerator.nextInt(FontColor.getSize()));
        Dot dot = new Dot(coordX,coordY,c,fontsize);
        mDots.insert(dot);

    }
    public void clearDot(View view){
        mDots.clear();
    }

    @Override
    public void onDotsChange(Dots dots) {
        mAdapter.notifyDataSetChanged();
        mDotView.invalidate();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, MENU_DELETE_ITEM,Menu.NONE,R.string.Delete);
        menu.add(Menu.NONE, MENU_EDIT_ITEM,Menu.NONE,"Edit");
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case MENU_DELETE_ITEM:
                confirmDelete(info.position);
                return true;
            case MENU_EDIT_ITEM:
                editDot(info.position);

        }

        return super.onContextItemSelected(item);
    }

    private void editDot(final int position) {
        View view = getLayoutInflater().inflate(R.layout.editdialog,null);
        final EditText edtCoordX = (EditText) view.findViewById(R.id.edtCoordX);
        final EditText edtCoordY = (EditText) view.findViewById(R.id.edtCoordY);
        Dot dot = mDots.get(position);
        edtCoordX.setText(String.valueOf(dot.getCoordX()));
        edtCoordY.setText(String.valueOf(dot.getCoordY()));

        new AlertDialog.Builder(this).setTitle("Edit").setView(view).setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int coordX =  Integer.parseInt(edtCoordX.getText().toString()) ;
                int coordY = Integer.parseInt(edtCoordY.getText().toString());
                mDots.edit(position, coordX, coordY);
            }
        }).setNegativeButton(getString(R.string.no),null).show();
    }

    private void confirmDelete(final int position) {
        new AlertDialog.Builder(this).setTitle("ConfirmDelete").setMessage("Are You Sure?").setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDots.delete(position);

            }
        }).setNegativeButton(getString(R.string.no),null).show();
    }

    @Override
    public void onDotsTouch(DotsView dotsView, int coordX, int coordY) {
        mDots.insert(new Dot(coordX,coordY));
    }
}
