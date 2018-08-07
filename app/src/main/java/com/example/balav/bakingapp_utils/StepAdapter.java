package com.example.balav.bakingapp_utils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.balav.bakingapp_utils.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends BaseAdapter
         {
             private static final String TAG = StepAdapter.class.getSimpleName();
             private int mNumberItems;
             private Context mContext;
             private List<Step> mStep;
             public  StepAdapter(Context context, List<Step> stepList){
                 Log.v (TAG,"Size of Steps -->"+stepList.size ());
                 mNumberItems = stepList.size ();
                 mStep =stepList;
                 mContext = context;
             }

             @Override
             public int getCount() {
                 return mNumberItems;
             }

             @Override
             public Object getItem(int position) {
                 return null;
             }

             @Override
             public long getItemId(int position) {
                 return 0;
             }

             @Override
             public View getView(int position, View convertView, ViewGroup parent) {
                 TextView stepText;
                 if(convertView == null){
                     stepText = new TextView (mContext);
                 }else{
                     stepText=(TextView)convertView;
                 }
                 Log.v (TAG, "Step-->"+mStep.get (position));
                 Log.v (TAG, "Step Desc-->"+mStep.get (position).getShortDescription ());
                 stepText.setText (String.valueOf (position+1)+". "+mStep.get(position).getShortDescription ());
                 stepText.setTextSize (25);
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                     stepText.setTextAlignment (View.TEXT_ALIGNMENT_VIEW_START);
                 }
                 return stepText;
             }
/*    private static final String TAG = StepAdapter.class.getSimpleName();
    private int mNumberItems;
    private Context mContext;
    private List<Step> mStep;



    public  StepAdapter(List<Step> stepList){
        Log.v (TAG,"Size of Steps -->"+stepList.size ());
        mNumberItems = stepList.size ();
        mStep =stepList;
    }
    @NonNull
    @Override
    public StepAdapter.StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        int layoutIdForListItem = R.layout.steps_card;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        StepAdapter.StepViewHolder viewHolder = new StepAdapter.StepViewHolder (view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Log.v(TAG, "#" + position);
        holder.bind (position);
    }


    @Override
    public int getItemCount() {        return mNumberItems;    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stepText;
        public StepViewHolder(View itemView) {
            super (itemView);
            stepText = (TextView)itemView.findViewById (R.id.tv_step);
            stepText.setOnClickListener (StepAdapter.StepViewHolder.this);
        }
        void bind(int listIndex) {
            Log.v (TAG, "Step-->"+mStep.get (listIndex));
            Log.v (TAG, "Step Desc-->"+mStep.get (listIndex).getShortDescription ());
            stepText.setText (mStep.get (listIndex).getShortDescription ());
        }


        @Override
        public void onClick(View v) {
            Log.v ("onClick-->","----|"+getAdapterPosition ()+"|");
            launchDetailActivity (getAdapterPosition ());
        }
        private void launchDetailActivity(int id) {
            Intent intent = new Intent(mContext, StepDetail.class);
            intent.putExtra (StepDetail.STEP_ID,id);
            intent.putParcelableArrayListExtra (StepDetail.STEP_KEY, (ArrayList)mStep);
            Log.v (TAG,"Recipe sending Object.."+mStep.get (id));
            Log.v (TAG,"Recipe sending ..."+mStep.get (id).getDescription ());
            mContext.startActivity(intent);
        }
    }*/
}
