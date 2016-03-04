package br.com.spiderman.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.spiderman.R;
import br.com.spiderman.domain.model.Comic;
import br.com.spiderman.domain.model.Thumbnail;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Caramelo on 03/03/2016.
 */
public class ComicListAdapter extends BaseAdapter<Comic> {

    public static final int TYPE_LAST = 1;

    public ComicListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        int count = super.getItemCount();
        if (count == 0) return count;
        return count + 1;
    }

    @Override
    public int getItemViewType(int position) {
        int count = getItemCount();
        return position == count - 1 ? count > 0 ? TYPE_LAST : 0 : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == TYPE_LAST) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_copyright, parent, false);
            return new CopyrightHolder(view);
        }

        view = LayoutInflater.from(context).inflate(R.layout.adapter_comic_list, parent, false);
        return new ComicHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CopyrightHolder)
            return;
        ComicHolder comicHolder = (ComicHolder) holder;
        comicHolder.bind(position);
    }

    class ComicHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivComic)
        ImageView ivComic;
        @Bind(R.id.tvNumber)
        TextView tvNumber;

        int mPosition;

        public ComicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null)
                        mOnItemClickListener.onItemClick(ComicListAdapter.this, ivComic, mPosition);
                }
            });
        }

        public void bind(int position) {
            mPosition = position;

            Comic comic = getItem(position);

            Glide.with(context)
                    .load(comic.getThumbnail().getPath(Thumbnail.PORTRAIT_MEDIUM))
                    .into(ivComic);

            tvNumber.setText(String.format("#%1$s", comic.getIssueNumber()));
        }
    }

    class CopyrightHolder extends RecyclerView.ViewHolder {
        public CopyrightHolder(View itemView) {
            super(itemView);
        }
    }
}
