package com.example.bob.myfirstar;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.1;
    ViewRenderable viewRenderableFactory; //CompletableFuture<ViewRenderable> viewRenderable;
    HashMap<Anchor, EditText> edtTxtFinder = new HashMap<Anchor, EditText>();
    EditText keyboard;
    EditText focusedEditText;
    EditText newEdtTxtView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }
        setContentView(R.layout.activity_main);

        keyboard = findViewById(R.id.keyboardInput);
        keyboard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("dhl", "In beforeTextChanged.");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("dhl", "In onTextChanged.");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("dhl", "In the afterTextChanged");
                if(s != null && s.length() > 0 && s.charAt(s.length() - 1) == '\n'){
                    //dp something
                    try {
                        focusedEditText.setText(keyboard.getText().toString());
                        keyboard.setText("");
                    } catch (Exception e){
                        Log.d("dhl", e.toString());
                    }
                }
            }
        });

        ArFragment arFragment = (ArFragment) getSupportFragmentManager()
                .findFragmentById(R.id.ux_fragment);

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {

                    //Grabs all the anchors from the given plane,
                    if(!plane.getAnchors().isEmpty()){
                        Collection<Anchor> anchors = plane.getAnchors();
                        //and detaches them, making'em disappear from the Scene.
                        for(Anchor anchorOnPlane : anchors){
                            anchorOnPlane.detach();
                        }
                        return;
                    }

                    //Will have to generate an EditText renderable here at this moment.
                    newEdtTxtView = createEditText();
                    newEdtTxtView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            focusedEditText = (EditText)v;
                            return true;
                        }
                    });

                    //The EditText view might be not showing immediately because builder()
                    //runs asynchrounously.
                    ViewRenderable.builder()
                            .setView(this, newEdtTxtView)
                            .build().thenAccept(renderable -> viewRenderableFactory = renderable);

                    // Create the Anchor.
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    // Create the transformable ViewRederable and add it to the anchor.
                    TransformableNode saiyan = new TransformableNode(arFragment.getTransformationSystem());
                    saiyan.setParent(anchorNode);
                    saiyan.setRenderable(viewRenderableFactory);
                    saiyan.select();

                    edtTxtFinder.put(anchor, newEdtTxtView);

                    if (viewRenderableFactory == null) {
                        return;
                    }
                });
    }

    public EditText createEditText(){
        Log.i("dhl", "");
        EditText newEditText = (EditText) getLayoutInflater().inflate(R.layout.my_renderable, null);;
        newEditText.setText("Empty TextView");
        return newEditText;
    }

    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.1 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.1 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }

}
