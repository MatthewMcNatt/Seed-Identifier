package com.example.seedidentifier;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;

import com.example.seedidentifier.ml.Prototype2;
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
* Matthew 3/21/2022
*  debug statements removed and wrapper completed
* 4/22/2022
*/

public class ImageAnalyzer implements Serializable {

    private Context context;
    private Prototype2 model;
    private Seed_Database data;
    private static final long serialVersionUID = 1;

    public ImageAnalyzer(Context c, Seed_Database d){
        context = c;
        data = d;
    }

    //SEE DOCUMENTATION FOR DETAILS
    public Seed analyzeImage(Bitmap bitmap){
        try{

            //creates model and then feeds it tensor image
            model = Prototype2.newInstance(context);
            TensorImage image = TensorImage.fromBitmap(bitmap);

            //runs model and creates a list of category objects that contain both label and probability
            Prototype2.Outputs output = model.process(image);
            List<Category> probability = output.getProbabilityAsCategoryList();

            //close the model
            model.close();



            Collections.sort(probability, new SortByProbability());


            if(probability.size() != 0){
                //THIS IS THE ISSUE
                return data.findSeed(probability.get(0).getLabel());
            }

        }catch(Exception e){
            System.out.println("error in ML model");
        }
        return null;

    }

    /*
        The exact same message but it allows the return of
        The match score from the Ml Model for transparency and
        UI display
    */
    //SEE DOCUMENTATION FOR DETAILS
    public MLPackage analyzeImagePackage(Bitmap bitmap){
        Seed s;
        float f;
        MLPackage r;
        try{


            //creates model and then feeds it tensor image
            model = Prototype2.newInstance(context);
            TensorImage image = TensorImage.fromBitmap(bitmap);

            //runs model and creates a list of category objects that contain both label and probability
            Prototype2.Outputs output = model.process(image);
            List<Category> probability = output.getProbabilityAsCategoryList();

            //close the model
            model.close();


            Collections.sort(probability, new SortByProbability());


            if(probability.size() != 0){
                //THIS IS THE ISSUE
                s =  data.findSeed(probability.get(0).getLabel());
                f = probability.get(0).getScore();
                r = new MLPackage(s, f);
                return r;

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
        return Float.compare(b.getScore(), a.getScore());
    }
}
