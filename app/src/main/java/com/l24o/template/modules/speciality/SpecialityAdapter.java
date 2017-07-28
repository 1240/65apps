package com.l24o.template.modules.speciality;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.l24o.template.R;
import com.l24o.template.common.IItemClickListener;
import com.l24o.template.data.realm.models.RealmSpecialty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.SpecialityVH> {

    private final List<RealmSpecialty> dataList;
    private final IItemClickListener<RealmSpecialty> clickListener;

    public SpecialityAdapter(List<RealmSpecialty> dataList, IItemClickListener<RealmSpecialty> clickListener) {
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    @Override
    public SpecialityVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality, parent, false);
        return new SpecialityVH(view);
    }

    @Override
    public void onBindViewHolder(SpecialityVH holder, int position) {
        holder.bind(dataList.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class SpecialityVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        public SpecialityVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(RealmSpecialty item, IItemClickListener<RealmSpecialty> clickListener) {
            itemView.setOnClickListener(v -> clickListener.onItemClick(item));
            tvName.setText(item.getName());
        }
    }
}
