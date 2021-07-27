package com.soft.credit911.ui.Document.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.soft.credit911.R;
import com.soft.credit911.adaptor.DocumentDetailsAdapter;
import com.soft.credit911.adaptor.OtherDocumentDetailsAdapter;
import com.soft.credit911.datamodel.DocumentModel;
import com.soft.credit911.datamodel.OtherDocumentModel;
import com.soft.credit911.databinding.ActivityDocumentBinding;
import com.soft.credit911.databinding.ToolbarBinding;

import java.util.ArrayList;

public class DocumentActivity extends AppCompatActivity {
    private static final int SELECT_PICTURES = 0;
    private static final int PICK_FROM_CAMERA = 1;
    private ActivityDocumentBinding layoutBinding;
    private ToolbarBinding toolbarBinding;
    private DocumentDetailsAdapter documentDetailsAdapter;
    private OtherDocumentDetailsAdapter otherDocumentDetailsAdapter;
    private ArrayList<OtherDocumentModel> otherDocumentModels = new ArrayList<>();
    private ArrayList<DocumentModel> documentModels = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = ActivityDocumentBinding.inflate(getLayoutInflater());
        View view = layoutBinding.getRoot();
        setContentView(view);

        toolbarBinding = layoutBinding.toolbarLayout;
        toolbarBinding.toolbarTitle.setText("Document");
        toolbarBinding.navigationIcon.setOnClickListener(v -> {
            onBackPressed();
        });

        documentDetailsAdapter = new DocumentDetailsAdapter(this);
        layoutBinding.rvDocument.setAdapter(documentDetailsAdapter);

        DocumentModel document = new DocumentModel();
        document.setUserLicence("Driving Licence (Font)");
        document.setUserStatus("pending");
        document.setCheckImage(R.drawable.ic__check_circle);
        documentModels.add(document);

        DocumentModel document1 = new DocumentModel();
        document1.setUserLicence("Driving Licence (Back)");
        document1.setUserStatus("pending");
        document1.setCheckImage(R.drawable.ic__check_circle);
        documentModels.add(document1);

        DocumentModel document2 = new DocumentModel();
        document2.setUserLicence("Social Security Number");
        document2.setUserStatus("Completed");
        document2.setCheckImage(R.drawable.ic_check_circle_green);
        documentModels.add(document2);

        DocumentModel document3 = new DocumentModel();
        document3.setUserLicence("Residence Proof");
        document3.setUserStatus("Rejected");
        document3.setCheckImage(R.drawable.alert);
        documentModels.add(document3);

        documentDetailsAdapter.addList(documentModels);


        otherDocumentDetailsAdapter = new OtherDocumentDetailsAdapter(this);
        layoutBinding.rvOtherDocument.setAdapter(otherDocumentDetailsAdapter);

        OtherDocumentModel otherDocument = new OtherDocumentModel();
        otherDocument.setUserLicence("Other Document 1");
        otherDocument.setUserStatus("....");
        otherDocument.setCheckImage(R.drawable.ic__check_circle);
        otherDocumentModels.add(otherDocument);

        OtherDocumentModel otherDocument2 = new OtherDocumentModel();
        otherDocument2.setUserLicence("Other Document 2");
        otherDocument2.setUserStatus("....");
        otherDocument2.setCheckImage(R.drawable.ic__check_circle);
        otherDocumentModels.add(otherDocument2);

        otherDocumentDetailsAdapter.addList(otherDocumentModels);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case SELECT_PICTURES:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount();
                        for (int i = 0; i < count; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        }

                    }
                } else if (data.getData() != null) {
                    String imagePath = data.getData().getPath();

                }
                break;
            case PICK_FROM_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, PICK_FROM_CAMERA);
                }
                break;
        }
    }
}