package edu.jsu.mcis.cs408.memopad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import edu.jsu.mcis.cs408.memopad.databinding.NoteItemBinding;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private NoteItemBinding binding;
    private List<Note> data;

    public RecyclerViewAdapter(String data){this.data = data;};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        binding = MemoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder holder = new RecyclerView.ViewHolder(binding.getRoot());
        return holder;

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setNote(data.get(position));
        holder.bindData;
    }

    @Override
    public int getItemCount(){ return data.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private Note note;
        private TextView idView;

        public ViewHolder(View idView) {super(itemView);}

        public Note getNote(){return note;}

        public void setNote(Note note ){this.note = note;}

        public void bindData(){
            if (idView == null){
                idView = (TextView) itemView.findViewById(R.id.idView);
            }
            idView.setText(note.getId());
        }
    }
}
