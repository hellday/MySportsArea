package com.example.terry.mysportsareaview;

/**
 * Created by Terry on 17/04/2017.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

    public CommentAdapter(Context context, List<Comment> comments) {
        super(context, 0, comments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_comments, parent, false);
        }

        CommentViewHolder viewHolder = (CommentViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CommentViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Comment comment = getItem(position);
        viewHolder.pseudo.setText(comment.getPseudo());
        viewHolder.text.setText(comment.getText());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(comment.getColor()));

        return convertView;
    }

    private class CommentViewHolder{
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;

    }
}