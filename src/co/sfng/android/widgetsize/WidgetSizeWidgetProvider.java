package co.sfng.android.widgetsize;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;


public class WidgetSizeWidgetProvider extends AppWidgetProvider {
	private static final String LOG_TAG = WidgetSizeWidgetProvider.class.toString();

	@Override
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
			int appWidgetId, Bundle newOptions) {
		Log.i(LOG_TAG, "onAppWidgetOptionsChanges()");
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,	newOptions);
		render(context, appWidgetManager, appWidgetId);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i(LOG_TAG, "onUpdate()");
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		for (int appWidgetId: appWidgetIds) {
			render(context, appWidgetManager, appWidgetId);
		}
	}

	private void render(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
		Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
		int minWidthInDp = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
		int maxWidthInDp = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
		int minHeightInDp = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
		int maxHeightInDp = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);

		RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget);
		view.setTextViewText(R.id.sizeText, String.format("[%d - %d] x [%d - %d]",
					minWidthInDp, maxWidthInDp, minHeightInDp, maxHeightInDp));
		appWidgetManager.updateAppWidget(appWidgetId, view);
	}
}
