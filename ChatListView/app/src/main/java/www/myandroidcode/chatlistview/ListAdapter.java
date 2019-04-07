package www.myandroidcode.chatlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;


public class ListAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    String[] name,message;
    int[] image;
    LayoutInflater inflater;

    public ListAdapter(Context context, String[] name,String[] message,int[] image) {
        this.context = context;
        this.name = name;
        this.message = message;
        this.image = image;
    }

    @Override
    public int getCount() {
        return name.length;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView txtname,txtmessage;
        CircularImageView imagename;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item_list, parent, false);

        // Locate the TextViews in listview_item.xml
        txtname = (TextView) itemView.findViewById(R.id.txt_name);

        txtmessage = (TextView) itemView.findViewById(R.id.txt_msg);

        // Locate the ImageView in listview_item.xml
        imagename = (CircularImageView) itemView.findViewById(R.id.image_data);

        // Capture position and set to the TextViews
        txtname.setText(name[position]);

        txtmessage.setText(message[position]);


        // Capture position and set to the ImageView
        imagename.setImageResource(image[position]);

        return itemView;
    }
}
