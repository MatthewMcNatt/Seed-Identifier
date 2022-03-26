package com.example.seedidentifier;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;

import com.example.seedidentifier.ml.Protoype1;

import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/*Wrapper for ML
* Matthew 3/21/2022*/
public class ImageAnalyzer implements Serializable {

    private Context context;
    private Protoype1 model;
    private Seed_Database data;
    private static final long serialVersionUID = 1;

    public ImageAnalyzer(Context c, Seed_Database d){
        context = c;
        data = d;
    }
    public Seed analyzeImage(Bitmap bitmap){
        try{
            model = Protoype1.newInstance(context);
            TensorImage image = TensorImage.fromBitmap(bitmap);
            Protoype1.Outputs output = model.process(image);
            List<Category> probability = output.getProbabilityAsCategoryList();
            Collections.sort(probability, new SortByProbability());
            if(probability.size() != 0){
                return data.findSeed(probability.get(0).getLabel());
            }

        }catch(Exception e){
            System.out.println("error in ML model");
        }
        return null;

    }
}

class SortByProbability implements Comparator<Category>
{
    public int compare(Category a, Category b)
    {
        return (int) (a.getScore() - b.getScore());
    }
}
