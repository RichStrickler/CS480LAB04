package edu.jsu.mcis.cs408.dbexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import edu.jsu.mcis.cs408.dbexample.databinding.ContactItemBinding;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ContactItemBinding binding;
    private List<String> data;

    public RecyclerViewAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ContactItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMemo(data.get(position));
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private String memo;
        private TextView memoText;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public void bindData() {

            if (memoText == null) {
                memoText = (TextView) itemView.findViewById(R.id.memoTextLabel);
            }
            memoText.setText(memo);

        }

    }

}