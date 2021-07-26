package com.soft.credit911.Document.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.soft.credit911.R;
import com.soft.credit911.Document.Model.DocumentModel;
import com.soft.credit911.databinding.DocumentItemListBinding;
import java.util.ArrayList;
import java.util.List;


public class DocumentDetailsAdapter extends RecyclerView.Adapter<DocumentDetailsAdapter.DocumentViewHolder> {
    private static final int PICK_FROM_CAMERA = 1;
    public List<DocumentModel> documentModels = new ArrayList<>();
    Context context;
    Dialog dialog;
    private static final int FILE_SELECT_CODE = 0;


    public DocumentDetailsAdapter(Context context) {
        this.context = context;
    }

    public void addList(ArrayList<DocumentModel> documentModelArrayList) {
        this.documentModels = documentModelArrayList;
        notifyDataSetChanged();
    }

    @Override
    public DocumentDetailsAdapter.DocumentViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        DocumentItemListBinding documentItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.document_item_list, viewGroup, false);
        return new DocumentViewHolder(documentItemListBinding);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(DocumentViewHolder holder, int position) {
        DocumentModel documentModelList = documentModels.get(position);

        switch (documentModelList.getUserStatus()) {
            case "Completed":
                holder.binding.mainLayoutLl.setBackgroundColor(context.getResources().getColor(R.color.light_green));
                holder.binding.tvUserStatus.setTextColor(context.getResources().getColor(R.color.green));
                holder.binding.tvUserLicence.setTextColor(context.getResources().getColor(R.color.black));
                break;
            case "Rejected":
                holder.binding.mainLayoutLl.setBackgroundColor(context.getResources().getColor(R.color.darkOrange));
                holder.binding.tvUserStatus.setTextColor(context.getResources().getColor(R.color.white));
                holder.binding.tvUserLicence.setTextColor(context.getResources().getColor(R.color.white));
                break;
            case "pending":
                holder.binding.mainLayoutLl.setBackgroundColor(context.getResources().getColor(R.color.white));
                break;
        }

        holder.binding.tvUserLicence.setText(documentModelList.getUserLicence());
        holder.binding.tvUserStatus.setText(documentModelList.getUserStatus());
        Glide.with(context)
                .load(documentModelList.getCheckImage())
                .placeholder(R.drawable.ic__check_circle)
                .into(holder.binding.ivCheck);




        holder.binding.mainLayoutLl.setOnClickListener(v -> {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.document_dailog_box);


            TextView document = dialog.findViewById(R.id.tv_Documents);
            TextView takePhoto = dialog.findViewById(R.id.tv_Take_photo);
            TextView gallery = dialog.findViewById(R.id.tv_Gallery);


            gallery.setOnClickListener(v1 -> {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                ((Activity) context).startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            });

            document.setOnClickListener(v1 -> {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    ((Activity) context).startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {

                    Toast.makeText(context, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }

            });
            takePhoto.setOnClickListener(v1 -> {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                ((Activity) context).startActivityForResult(cameraIntent, PICK_FROM_CAMERA);

            });
            dialog.show();
        });


    }


    @Override
    public int getItemCount() {
        return documentModels.size();
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder {
        public DocumentItemListBinding binding;

        public DocumentViewHolder(DocumentItemListBinding documentItemListBinding) {
            super(documentItemListBinding.getRoot());
            binding = documentItemListBinding;

        }

    }
}
