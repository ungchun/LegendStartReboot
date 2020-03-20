package com.example.myfragment1.MSMain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfragment1.R;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<String> title;
    //    private List<String> des_data;
    private List<String[]> tag;
    Context mcontext;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;


    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }


    //    private String btn = "btn";
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    Adapter(Context context, List<String> data, ArrayList<String[]> Tag) {
        this.layoutInflater = LayoutInflater.from(context);
        this.title = data;
        this.tag = Tag;
        mcontext = context;
    }

//    Adapter(Context context, List<String> des_data, List<String> data){
//        this.layoutInflater = LayoutInflater.from(context);
//        this.data = data;
//        this.des_data = des_data;
//        mcontext = context;
//    }

    class HeaderViewHolder extends ViewHolder {

        HeaderViewHolder(View headerView) {
            super(headerView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ms_recy_add, parent, false);
            holder = new HeaderViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ms_recy_test, parent, false);
            holder = new ViewHolder(view);
        }
        return (ViewHolder) holder;
//        return new ViewHolder(view);

//        View view = layoutInflater.inflate(R.layout.ms_recy_test, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return title.size()+1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String Title = title.get(position);
//        String des = des_data.get(position);
//        String[] Tag_1 = tag.get(position);
//        holder.textTitle.setText("실험");
//        holder.textTitle.setText(Title);
//        holder.textDescription.setText(des);

        if(holder instanceof HeaderViewHolder){
            //없어도 뜨네 ? ㅅㅂ 뭐지
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        }
        else{
            String Title = title.get(position-1);
            holder.textTitle.setText(Title);
//            String[] Tag_1 = tag.get(position-1);
////             tag가 0개일때
//            if (Tag_1.length == 0) {
//                holder.Total.setText(Integer.toString(Tag_1.length));
//            }
//            // tag가 1개일떄
//            else if (Tag_1.length == 1) {
//                holder.textDescription_1.setText(Tag_1[0]);
//                holder.textDescription_1.setVisibility(View.VISIBLE);
////                 Total은 나중에 거래처 갯수표현할꺼임
//                holder.Total.setText(Integer.toString(Tag_1.length));
//            }
//            // tag가 2개일떄
//            else if (Tag_1.length == 2) {
////           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]);
//                holder.textDescription_1.setText(Tag_1[0]);
//                holder.textDescription_1.setVisibility(View.VISIBLE);
//                holder.textDescription_2.setText(Tag_1[1]);
//                holder.textDescription_2.setVisibility(View.VISIBLE);
//                holder.Total.setText(Integer.toString(Tag_1.length));
//        }
//        // tag가 3개일떄
//            else if (Tag_1.length == 3) {
////           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]+" "+Tag_1[2]);
//                holder.textDescription_1.setText(Tag_1[0]);
//                holder.textDescription_1.setVisibility(View.VISIBLE);
//                holder.textDescription_2.setText(Tag_1[1]);
//                holder.textDescription_2.setVisibility(View.VISIBLE);
//                holder.textDescription_3.setText(Tag_1[2]);
//                holder.textDescription_3.setVisibility(View.VISIBLE);
//                holder.Total.setText(Integer.toString(Tag_1.length));
//            }
//            // tag가 4개일떄
//            else if (Tag_1.length == 4) {
////           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]+" "+Tag_1[2]+" "+Tag_1[3]);
//                holder.textDescription_1.setText(Tag_1[0]);
//                holder.textDescription_1.setVisibility(View.VISIBLE);
//                holder.textDescription_2.setText(Tag_1[1]);
//                holder.textDescription_2.setVisibility(View.VISIBLE);
//                holder.textDescription_3.setText(Tag_1[2]);
//                holder.textDescription_3.setVisibility(View.VISIBLE);
//                holder.textDescription_4.setText(Tag_1[3]);
//                holder.textDescription_4.setVisibility(View.VISIBLE);
//                holder.Total.setText(Integer.toString(Tag_1.length));
//            }
//            // tag가 5개일떄
//            else if (Tag_1.length == 5) {
////           holder.textDescription.setText(Tag_1[0]+" "+Tag_1[1]+" "+Tag_1[2]+" "+Tag_1[3]+" "+Tag_1[4]);
//                holder.textDescription_1.setText(Tag_1[0]);
//                holder.textDescription_1.setVisibility(View.VISIBLE);
//                holder.textDescription_2.setText(Tag_1[1]);
//                holder.textDescription_2.setVisibility(View.VISIBLE);
//                holder.textDescription_3.setText(Tag_1[2]);
//                holder.textDescription_3.setVisibility(View.VISIBLE);
//                holder.textDescription_4.setText(Tag_1[3]);
//                holder.textDescription_4.setVisibility(View.VISIBLE);
//                holder.textDescription_5.setText(Tag_1[4]);
//                holder.textDescription_5.setVisibility(View.VISIBLE);
//                holder.Total.setText(Integer.toString(Tag_1.length));
//            }

        }

//        holder.btnTitle.setText(btn);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription_1, textDescription_2, textDescription_3, textDescription_4, textDescription_5, Total;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            btnTitle = itemView.findViewById(R.id.recy_test_btn);
            textTitle = (TextView)itemView.findViewById(R.id.textView);
//            textDescription_1 = (TextView)itemView.findViewById(R.id.textView2);
//            textDescription_2 = (TextView)itemView.findViewById(R.id.textView3);
//            textDescription_3 = (TextView)itemView.findViewById(R.id.textView4);
//            textDescription_4 = (TextView)itemView.findViewById(R.id.textView5);
//            textDescription_5 = (TextView)itemView.findViewById(R.id.textView6);
            Total = (TextView)itemView.findViewById(R.id.textView7);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if(pos == 0){
                            Toast.makeText(mcontext,"add Btn",Toast.LENGTH_SHORT).show();
                        }
                        else if (mListener != null) {
                            mListener.onItemClick(v, pos-1);
                        }
                    }
                }
            });
        }
    }
}
