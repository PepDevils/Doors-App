package pt.pepdevils.doors.adapters;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.util.Elements;
import pt.pepdevils.doors.util.Helper;

import java.util.ArrayList;

/**
 * Created by pepdevils on 26/07/16.
 */
public class ItemsAdapter extends RecyclerView.Adapter {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    // The minimum amount of items to have for your current scroll position before loading more.
    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    private Activity a;
    private ArrayList<Elements> arrayList;

    private ImageLoader imageLoader;

  /*  imageLoader = ImageLoader.getInstance();
    imageLoader.init(ImageLoaderConfiguration.createDefault(a));*/


    //CONSTRUTOR
    public ItemsAdapter(Activity a, RecyclerView recyclerView, ArrayList<Elements> arrayList, ImageLoader imageLoader) {
        this.a = a;
        this.arrayList = arrayList;
        this.imageLoader = imageLoader;


        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_image_buttom_layout, parent, false);
            vh = new ItemsViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
            vh = new ProgressViewHolder(itemView);
        }
        return vh;

    }

    @Override
    public int getItemViewType(int position) {
        return arrayList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemsViewHolder) {

            Elements item = arrayList.get(position);

            String model = item.getModel();
            String line = item.getLine();
            String image_door = item.getImage_Door();


            line = Helper.CleanLine(line);


            ((ItemsViewHolder) holder).tx_line.setText(line);
            ((ItemsViewHolder) holder).tx_model.setText(model);
            Helper.DoorImageLoader(a, image_door, ((ItemsViewHolder) holder).imageButton, false, false, imageLoader);

            //set fonts
            ArrayList<TextView> textViewArrayList = new ArrayList<>();
            textViewArrayList.add(((ItemsViewHolder) holder).tx_line);
            textViewArrayList.add(((ItemsViewHolder) holder).tx_model);
            Helper.AplicarFontTextual(a, "Titillium-Regular", textViewArrayList);


        } else {

            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }


    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setLoaded() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public static class ItemsViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imageButton;
        public TextView tx_line;
        public TextView tx_model;

        public ItemsViewHolder(View view) {
            super(view);

            imageButton = (ImageButton) view.findViewById(R.id.imageButton);
            tx_line = (TextView) view.findViewById(R.id.line);
            tx_model = (TextView) view.findViewById(R.id.model);
        }

    }




}
