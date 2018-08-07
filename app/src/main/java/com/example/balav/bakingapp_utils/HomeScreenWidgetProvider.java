package com.example.balav.bakingapp_utils;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.content.ContentUris;
import android.content.ContentValues;

import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.balav.bakingapp_utils.MainActivity;
import com.example.balav.bakingapp_utils.R;
import com.example.balav.bakingapp_utils.data.IngredientContract;

import java.util.Random;

import static com.example.balav.bakingapp_utils.data.IngredientContract.BASE_CONTENT_URI;
import static com.example.balav.bakingapp_utils.data.IngredientContract.IngredientEntry.CONTENT_URI;

public class HomeScreenWidgetProvider extends AppWidgetProvider {
    private static final String TAG = HomeScreenWidgetProvider.class.getSimpleName ();

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.v(TAG , "In onUpdate");
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews (context.getPackageName (), R.layout.homescreen_widget_provider);

            fillIngredientsText(context,views);

            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent=PendingIntent.getActivity (context,0,intent,0);
            views.setOnClickPendingIntent (R.id.widget_text_ingredients,pendingIntent);
            appWidgetManager.updateAppWidget (appWidgetId, views);
        }
    }
    private void fillIngredientsText(Context mContext, RemoteViews views) {
        StringBuilder sb=new StringBuilder ();
        Uri PLANT_URI = CONTENT_URI;
        Cursor cursor = mContext.getContentResolver ().query(
                PLANT_URI,
                null,
                null,
                null,
                null
        );
        String RecipeName="";
        Log.v (TAG, "count-->" + cursor.getCount ());
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount ();i++) {
                int idIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry._ID);
                int recipeIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.RECIPE_NAME);
                int ingredientIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.INGREDIENT);
                int quantityIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.QUANTITY);
                int measureIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.MEASURE);
                RecipeName = cursor.getString (recipeIndex);
                String ingredient = cursor.getString (ingredientIndex);
                String measure = cursor.getString (measureIndex);
                double quantity = cursor.getDouble (quantityIndex);
                sb.append (String.valueOf (cursor.getLong (idIndex)) + "." + ingredient + " " + String.valueOf (quantity) +" "+ measure + "\n");
                cursor.moveToNext ();
            }
        }
        Log.v(TAG,"Ingredient Text -->"+sb.toString ());
        views.setTextViewText (R.id.widget_text_ingredients,sb.toString ());
        views.setTextViewText (R.id.widget_recipe,RecipeName);
    }


  /*  @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.v(TAG , "In onUpdate");
        //HomeScreenWidgetService.startActionUpdateIngredients(context);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews (context.getPackageName (), R.layout.homescreen_widget_provider);

            views.setTextViewText (R.id.widget_text_ingredients,getIngredientsText(context));

            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent=PendingIntent.getActivity (context,0,intent,0);
            views.setOnClickPendingIntent (R.id.widget_text_ingredients,pendingIntent);
            Intent ingredientIntent= new Intent (context,HomeScreenWidgetService.class);
            ingredientIntent.setAction (HomeScreenWidgetService.ACTION_UPDATE_INGREDIENTS);
            PendingIntent ingredientPendingIntent = PendingIntent.getService (context,0,ingredientIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            //  views.setOnClickPendingIntent (R.id.widget_text_ingredients,ingredientPendingIntent);
            appWidgetManager.updateAppWidget (appWidgetId, views);
        }
    }

    private String getIngredientsText(Context mContext) {
        StringBuilder sb=new StringBuilder ();
        Uri PLANT_URI = CONTENT_URI;
        Cursor cursor = mContext.getContentResolver ().query(
                PLANT_URI,
                null,
                null,
                null,
                null
        );
        String RecipeName="";
        Log.v (TAG, "count-->" + cursor.getCount ());
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for(int i=0;i<cursor.getCount ();i++) {
                int idIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry._ID);
                int recipeIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.RECIPE_NAME);
                int ingredientIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.INGREDIENT);
                int quantityIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.QUANTITY);
                int measureIndex = cursor.getColumnIndex (IngredientContract.IngredientEntry.MEASURE);
                RecipeName = cursor.getString (recipeIndex);
                String ingredient = cursor.getString (ingredientIndex);
                String measure = cursor.getString (measureIndex);
                double quantity = cursor.getDouble (quantityIndex);
                sb.append (String.valueOf (cursor.getLong (idIndex)) + "." + ingredient + " " + String.valueOf (quantity) +" "+ measure + "\n");
                cursor.moveToNext ();
            }
        }
        Log.v(TAG,"Ingredient Text -->"+sb.toString ());
        return RecipeName+"\n"+sb.toString ();
    }

    public static void updateIngredientsWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds,String strIngredientText) {
        Log.v(TAG ,"in updateIngredientsWidgets");
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews (context.getPackageName (), R.layout.homescreen_widget_provider);

            views.setTextViewText (R.id.widget_text_ingredients,strIngredientText);

            Intent intent = new Intent(context, MainActivity.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent=PendingIntent.getActivity (context,0,intent,0);
            views.setOnClickPendingIntent (R.id.widget_text_ingredients,pendingIntent);
            Intent ingredientIntent= new Intent (context,HomeScreenWidgetService.class);
            ingredientIntent.setAction (HomeScreenWidgetService.ACTION_UPDATE_INGREDIENTS);
            PendingIntent ingredientPendingIntent = PendingIntent.getService (context,0,ingredientIntent,PendingIntent.FLAG_UPDATE_CURRENT);
          //  views.setOnClickPendingIntent (R.id.widget_text_ingredients,ingredientPendingIntent);
            appWidgetManager.updateAppWidget (appWidgetId, views);
        }
    }*/
}
