package com.l24o.template.modules.person;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.l24o.template.R;
import com.l24o.template.common.IItemClickListener;
import com.l24o.template.common.utils.AgeUtils;
import com.l24o.template.data.realm.models.RealmPerson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonVH> {

    private final List<RealmPerson> dataList;
    private final IItemClickListener<RealmPerson> clickListener;

    public PersonAdapter(List<RealmPerson> dataList, IItemClickListener<RealmPerson> clickListener) {
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    @Override
    public PersonVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new PersonVH(view);
    }

    @Override
    public void onBindViewHolder(PersonVH holder, int position) {
        holder.bind(dataList.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class PersonVH extends RecyclerView.ViewHolder {

        @BindView(R.id.tvAge)
        TextView tvAge;
        @BindView(R.id.tvFio)
        TextView tvFio;
        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;

        public PersonVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(RealmPerson person, IItemClickListener<RealmPerson> clickListener) {
            Glide.with(itemView.getContext())
                    .load(person.getAvatarUrl())
                    .placeholder(R.drawable.ic_account_box_black_48dp)
                    .error(R.drawable.ic_broken_image_black_48dp)
                    .into(ivAvatar);
            itemView.setOnClickListener(v -> clickListener.onItemClick(person));
            tvFio.setText(itemView.getContext().getString(R.string.person_fio, person.getFirstName(), person.getLastName()));
            if (person.getBirthday() != null) {
                int age = person.getAge();
                tvAge.setText(itemView.getContext().getString(AgeUtils.getAgeEnds(age), age));
            } else {
                tvAge.setText(itemView.getContext().getString(R.string.age_null));
            }

        }
    }
}
