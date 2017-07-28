package com.l24o.template.common.mvp;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.l24o.template.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Alexander Popov on 27/07/2017.
 */

abstract public class BaseListActivity<M> extends MvpActivity implements MvpContract.IListView<M> {

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.emptyMessageTextView)
    TextView emptyMessageTextView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    protected List<M> dataset = new ArrayList<>();
    protected RecyclerView.Adapter<?> adapter = null;

    @Override
    protected int getActivityLayoutRes() {
        return R.layout.layout_recycler_view;
    }

    @Override
    public void setLoadingVisible(boolean isVisible) {
        if (!swipeRefreshLayout.isRefreshing()) {
            progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showData(List<M> dataList) {
        dataset.clear();
        dataset.addAll(dataList);
        swipeRefreshLayout.setRefreshing(false);
        setLoadingVisible(false);
        setEmptyViewVisible(dataList.isEmpty());
        adapter.notifyDataSetChanged();
    }

    protected void setEmptyViewVisible(boolean isVisible) {
        emptyMessageTextView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    protected void onSwipeToRefresh() {
    }

    protected void initViews() {
        emptyMessageTextView.setText(getEmptyText());
        swipeRefreshLayout.setOnRefreshListener(this::onSwipeToRefresh);

        int accentColorId = ContextCompat.getColor(this, R.color.colorPrimary);
        swipeRefreshLayout.setColorSchemeColors(accentColorId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = createAdapter();
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setEnabled(isSwipeRefreshLayoutEnable());
    }

    protected boolean isSwipeRefreshLayoutEnable() {
        return false;
    }

    protected abstract String getEmptyText();

    @NonNull
    protected abstract RecyclerView.Adapter<?> createAdapter();
}
