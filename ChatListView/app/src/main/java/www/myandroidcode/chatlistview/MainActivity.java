package www.myandroidcode.chatlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ListAdapter adapter;
    String[] name,message;

    int[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = new String[]{ "Christopea", "Davidbeck", "Lissameri", "DanielJack", "Markburg","Tommy Gilf","Lucifer","Merilin Chris","Emma Watson" };

        message = new String[]{ "Hey, How are you ?", "Sound good to me", "I am hearing musics", "join me at evening", "What kind of movie do you like ?", "Hi.Nice to meet you", "Do reach me soon","Let us go for the jack part ?","Sure, See you in a bit!"};


        image = new int[] { R.drawable.pic2,R.drawable.pic3,
                R.drawable.pic5,R.drawable.pic4,
                R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10};

        // Locate the ListView in listview_main.xml
        list = (ListView)findViewById(R.id.mylist);

        // Pass results to ListViewAdapter Class
        adapter = new ListAdapter(this, name,message,image);
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        // Capture ListView item click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(MainActivity.this,"You have selected :"+name[position], Toast.LENGTH_SHORT).show();


            }

        });

    }
}
