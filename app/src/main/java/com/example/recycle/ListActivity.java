package com.example.recycle;

//import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycle.adapter.StarAdapter;
import com.example.recycle.beans.Star;
import com.example.recycle.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;
    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyApp", "I am here List");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init() {

        service.create(new Star("Johnny depp", "https://i.pinimg.com/originals/b1/f7/ea/b1f7ea10524a0fd40fb3a1d83324c618.jpg", 3.5f));
        service.create(new Star("Katy Perry", "https://i.pinimg.com/originals/56/53/7f/56537ff04f7e8dd3872565e17c0c49a2.jpg", 3));
        //  service.create(new Star("michelle rodriguez",
        //         "http://www.starsphotos.com/resize.php?id=1120", 5));
        service.create(new Star("Charlie Puth", "https://i.pinimg.com/originals/51/d0/04/51d0042fd10015fee29de591ab9ab08d.jpg", 1));
        service.create(new Star("Taylor swift", "https://i.pinimg.com/564x/c8/3b/8c/c83b8cf3f6a5353130c3a07c96a139c9.jpg", 5));
        service.create(new Star("Rick Grimes", "https://i.pinimg.com/originals/e0/ee/62/e0ee626bdd78bd308cf44d699f1c7268.jpg", 3));
        service.create(new Star("Bryan Cranston", "https://s2.glbimg.com/L3ncejqYbhmDbt1W2H14RVSaPrI=/smart/e.glbimg.com/og/ed/f/original/2015/06/22/bryan-cranston-danca-musica-com-nome-de-seu-personagem-em-breaking-bad.jpg", 4.5f));
        service.create(new Star("Krysten Ritter", "https://i.pinimg.com/originals/39/9c/5f/399c5fdddcb21eb4a69926af68f9ac36.jpg", 2));
        service.create(new Star("Hailee Steinfeld", "https://i.pinimg.com/originals/f4/fe/df/f4fedf98afd7629ef30abccc0adb7057.jpg", 2.5f));
        service.create(new Star("Robert Pattins", "https://i.pinimg.com/originals/99/dd/d5/99ddd50f27443f445138e4cbc92ae8ae.jpg", 5));
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }
}


