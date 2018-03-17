package app.hackit.hackamigo.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


import java.io.IOException;

import app.hackit.hackamigo.R;
import app.hackit.hackamigo.utils.AppConstants;

public class DetectDiseaseActivity extends BaseActivity implements
        View.OnClickListener {

    Button btnDetect;
    Bitmap photo;
    private ImageView mDetectImage;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private FloatingActionButton fabOpen, fabCamera, fabGallery;
    boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_disease);
        setUpToolbar(this);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        btnDetect = findViewById(R.id.button_detect);
        mDetectImage = findViewById(R.id.image_detect);
        fabOpen = findViewById(R.id.fab_open);
        fabCamera = findViewById(R.id.fab_camera);
        fabGallery = findViewById(R.id.fab_gallery);

        fabOpen.setOnClickListener(this);
        fabGallery.setOnClickListener(this);
        fabCamera.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (!isFABOpen) {
            super.onBackPressed();
        } else {
            closeFABMenu();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        closeFABMenu();

        if (resultCode == RESULT_OK) {

            if (requestCode == AppConstants.CAMERA_REQUEST) {

                photo = (Bitmap) data.getExtras().get("data");

                mDetectImage.setImageBitmap(photo);
            } else if (requestCode == AppConstants.PICK_IMAGE && null != data) {

                try {

                    photo = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    mDetectImage.setImageBitmap(photo);

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected int getToolbarID() {
        return R.id.detect_disease_toolbar;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fab_open:
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
                break;

            case R.id.fab_camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, AppConstants.CAMERA_REQUEST);
                break;

            case R.id.fab_gallery:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), AppConstants.PICK_IMAGE);
                break;
        }
    }

    private void showFABMenu() {
        isFABOpen = true;
        fabOpen.startAnimation(rotate_forward);
        fabCamera.startAnimation(fab_open);
        fabGallery.startAnimation(fab_open);
        fabCamera.setClickable(true);
        fabGallery.setClickable(true);
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fabOpen.startAnimation(rotate_backward);
        fabCamera.startAnimation(fab_close);
        fabGallery.startAnimation(fab_close);
        fabCamera.setClickable(false);
        fabGallery.setClickable(false);
    }
}
