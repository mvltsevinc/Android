package com.example.mvltsevinc.customlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter<Person> {
    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /*
    * Holds variable in a View
    * */
    static class ViewHolder {
        TextView name;
        TextView birthday;
        TextView sex;
    }

    public PersonListAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the persons informations
        String name = getItem(position).getName();
        String birthday = getItem(position).getBirthday();
        String sex = getItem(position).getSex();

        // Create the person object with informations
        Person person = new Person(name,birthday,sex);

        // Create the view result for showing the animation
        final View result;

        // ViewHolder object
        ViewHolder holder;

        if(convertView == null) // ListViewdaki o pozisyona hic gelinmemisse
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource,parent,false);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.birthday = (TextView) convertView.findViewById(R.id.textView2);
            holder.sex = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;
            convertView.setTag(holder); // setTag ile o pozisyonu bellege atiyor. Daha sonra tekrar gelince o pozisyona direkt bellekten aliyor
        }else{
            holder = (ViewHolder) convertView.getTag(); // getTag ile daha once o pozisyona geldiginde bellekte var onu alıyor
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(name);
        holder.birthday.setText(birthday);
        holder.sex.setText(sex);

        return convertView;
    }
}

