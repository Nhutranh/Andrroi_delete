package com.phanngocnhutranh.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.phanngocnhutranh.Giangvien.Giangvien;
import com.phanngocnhutranh.android_delete.MainActivity;
import com.phanngocnhutranh.android_delete.R;

import java.util.List;

public class Adapter extends BaseAdapter {

    MainActivity context;
    int item_layout;
    List<Giangvien> giangviens;

    public Adapter(MainActivity context, int item_layout, List<Giangvien> giangviens) {
        this.context = context;
        this.item_layout = item_layout;
        this.giangviens = giangviens;
    }

    @Override
    public int getCount() {
        return giangviens.size();
    }

    @Override
    public Object getItem(int position) {
        return giangviens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel hodel;
        if(convertView == null){
            hodel = new ViewHodel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, null);

            hodel.code = convertView.findViewById(R.id.txtcode);
            hodel.name = convertView.findViewById(R.id.txtname);
            hodel.classroom = convertView.findViewById(R.id.txtclassroom);

            convertView.setTag(hodel);
        }else {
            hodel = (ViewHodel) convertView.getTag();
        }

        Giangvien gv = giangviens.get(position);// lấy vị trí để set lại nội dung trong
        hodel.code.setText(String.valueOf(gv.getCode()));
        hodel.name.setText(String.valueOf(gv.getName()));
        hodel.classroom.setText(String.valueOf(gv.getClassroom()));

        return convertView;
    }

    public class ViewHodel{
        TextView code, name, classroom;
    }
}
