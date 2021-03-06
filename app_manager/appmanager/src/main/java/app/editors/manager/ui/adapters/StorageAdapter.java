package app.editors.manager.ui.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import app.editors.manager.R;
import app.editors.manager.app.Api;
import app.editors.manager.ui.adapters.base.BaseAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import lib.toolkit.base.managers.utils.UiUtils;

public class StorageAdapter extends BaseAdapter<String> {

    private final Context mContext;

    public StorageAdapter(@NonNull Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int typeHolder) {
        final View viewItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_storage_select_item, viewGroup, false);
        return new ViewHolderItem(viewItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ViewHolderItem mViewHolder = (ViewHolderItem) viewHolder;
        setContentByKey(mViewHolder, getItem(position));
    }

    private void setContentByKey(final ViewHolderItem mViewHolder, final String id) {
        mViewHolder.mStorageTitle.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        mViewHolder.mStorageImage.setAlpha(1.0f);
        mViewHolder.mStorageImage.setPadding(0, 0, 0, 0);

        if (!UiUtils.isTablet(mContext)) {
            final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) mViewHolder.mGuideline.getLayoutParams();
            params.guidePercent= 0.0f;
            mViewHolder.mGuideline.setLayoutParams(params);
        }

        switch (id) {
            case Api.Storage.BOXNET:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_box);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_box);
                break;
            case Api.Storage.DROPBOX:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_dropbox);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_drop_box);
                break;
            case Api.Storage.SHAREPOINT:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_sharepoint);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_share_point);
                break;
            case Api.Storage.GOOGLEDRIVE:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_google);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_google_drive);
                break;
            case Api.Storage.ONEDRIVE:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_onedrive);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_one_drive);
                break;
            case Api.Storage.YANDEX:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_yandex);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_yandex);
                break;
            case Api.Storage.OWNCLOUD:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_owncloud);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_own_cloud);
                break;
            case Api.Storage.NEXTCLOUD:
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_nextcloud);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_next_cloud);
                break;
            case Api.Storage.WEBDAV:
                final int padding = (int) mContext.getResources().getDimension(R.dimen.image_padding_icon);
                mViewHolder.mStorageImage.setImageResource(R.drawable.ic_storage_webdav);
                mViewHolder.mStorageImage.setAlpha(UiUtils.getFloatResource(mContext, R.dimen.alpha_medium));
                mViewHolder.mStorageImage.setPadding(padding, padding, padding, padding);
                mViewHolder.mStorageTitle.setText(R.string.storage_select_web_dav);
                break;
        }
    }

    protected class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.guideline)
        Guideline mGuideline;
        @BindView(R.id.storage_item_layout)
        ConstraintLayout mStorageLayout;
        @BindView(R.id.storage_item_image)
        AppCompatImageView mStorageImage;
        @BindView(R.id.storage_item_title)
        AppCompatTextView mStorageTitle;

        public ViewHolderItem(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, getLayoutPosition());
                }
            });
        }
    }

}
