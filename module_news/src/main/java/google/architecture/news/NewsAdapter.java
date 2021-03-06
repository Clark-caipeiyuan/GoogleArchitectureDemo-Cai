package google.architecture.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import google.architecture.coremodel.datamodel.http.entities.NewsData;
import google.architecture.news.databinding.NewsItemBinding;

/**
 * Created by Clark on 2022/02/28.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.GirlsViewHolder> {

    List<NewsData.ResultsBean> newsList;
    NewsItemClickCallback newsItemClickCallback;

    /**
     * 构造方法传入点击监听器
     * @param itemClickCallback
     */
    public NewsAdapter(@Nullable NewsItemClickCallback itemClickCallback) {
        newsItemClickCallback = itemClickCallback;
    }

    public void setGirlsList(final List<NewsData.ResultsBean> list){
        if(newsList == null){
            newsList = list;
            notifyItemRangeInserted(0, newsList.size());
        }else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return newsList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    NewsData.ResultsBean oldData = newsList.get(oldItemPosition);
                    NewsData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.getId() == newData.getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    NewsData.ResultsBean oldData = newsList.get(oldItemPosition);
                    NewsData.ResultsBean newData = list.get(newItemPosition);
                    return oldData.getId() == newData.getId()
                            && oldData.getHint() == newData.getHint()
                            && oldData.getTitle() == newData.getTitle()
                            && oldData.getImage_hue() == newData.getImage_hue();
                }
            });
            newsList = list;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public GirlsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item,
                        parent, false);
        binding.setCallback(newsItemClickCallback);
        return new GirlsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GirlsViewHolder holder, int position) {
        holder.binding.setNewsItem(newsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return newsList == null ? 0 : newsList.size();
    }

    static class GirlsViewHolder extends RecyclerView.ViewHolder {
        NewsItemBinding binding;
        public GirlsViewHolder(NewsItemBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
