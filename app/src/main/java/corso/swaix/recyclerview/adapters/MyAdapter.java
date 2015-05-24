package corso.swaix.recyclerview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import corso.swaix.recyclerview.R;
import corso.swaix.recyclerview.model.RowModel;

/**
 * Created by SwaiX on 24/05/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RowHolder> {
    private static final int HEADER = 1;
    private static final int ITEM = 2;
    private LayoutInflater inflater;
    private List<RowModel> data = Collections.emptyList();

    public MyAdapter(Context context, List<RowModel> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM) {
            View view = inflater.inflate(R.layout.adapter_layout, parent, false);
            RowHolder holder = new RowHolder(view);
            return holder;
        } else if (viewType == HEADER) {
            View view = inflater.inflate(R.layout.adapter_header_layout, parent, false);
            RowHolder holder = new RowHolder(view);
            return holder;
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types    correctly");
    }

    @Override
    public void onBindViewHolder(RowHolder holder, int position) {

        final int fPos = position;

        if (isHeader(position)) {
            final EditText editText = (EditText) holder.textView;
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add(editText, fPos);
                }
            });
        } else {
            RowModel current = data.get(position - 1);
            ((TextView) holder.textView).setText(current.getText());
            holder.imageView.setImageResource(current.getIconId());
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(fPos);
                }
            });
        }
    }

    private void add(EditText editText, int position) {
        RowModel newRow = new RowModel(R.drawable.ic_remove, editText.getText().toString());
        data.add(0, newRow);
        notifyItemInserted(0);
    }

    private void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        else
            return ITEM;
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RowHolder extends RecyclerView.ViewHolder {
        View textView;
        ImageView imageView;

        public RowHolder(View itemView) {
            super(itemView);
            textView = (View) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.action_button);
        }
    }


}
