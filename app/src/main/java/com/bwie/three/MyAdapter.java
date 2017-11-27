package com.bwie.three;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.three.bean.NewsBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 小傻瓜 on 2017/11/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
    private List<NewsBean.DataBean> dataBeanList;

    public MyAdapter(Context context, List<NewsBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.item_recycler,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_title.setText(dataBeanList.get(position).getTitle());
        holder.tv_content.setText(dataBeanList.get(position).getContent());
        holder.type.setText(dataBeanList.get(position).getType()+" ");
        Uri uri=Uri.parse(dataBeanList.get(position).getImage_url());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.facebook.setController(controller);
        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.setOnItemClickListener(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_title,tv_content,type;
        public SimpleDraweeView facebook;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_content=itemView.findViewById(R.id.tv_content);
            type=itemView.findViewById(R.id.type);
            facebook=itemView.findViewById(R.id.facebook);
        }
    }
    /**
     * 回调接口
     */
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void setOnItemClickListener(View v, int position);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
