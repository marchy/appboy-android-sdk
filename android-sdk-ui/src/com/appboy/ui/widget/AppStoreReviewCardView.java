package com.appboy.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appboy.Appboy;
import com.appboy.models.cards.AppStoreReviewCard;
import com.appboy.ui.R;
import com.appboy.ui.actions.GooglePlayAppDetailsAction;
import com.appboy.ui.actions.IAction;

public class AppStoreReviewCardView extends BaseCardView<AppStoreReviewCard> {
  private int mApplicationIconId;
  private final ImageView mImage;
  private final TextView mTitle;
  private final TextView mSubtitle;
  private IAction mCardAction;

  public AppStoreReviewCardView(Context context, int applicationIconId) {
    this(context, null, applicationIconId);
  }

  public AppStoreReviewCardView(final Context context, AppStoreReviewCard card, int applicationIconId) {
    super(context);
    mApplicationIconId = applicationIconId;
    mImage = (ImageView) findViewById(R.id.com_appboy_app_store_review_card_image);
    mTitle = (TextView) findViewById(R.id.com_appboy_app_store_review_card_title);
    mSubtitle = (TextView) findViewById(R.id.com_appboy_app_store_review_card_subtitle);

    if (card != null) {
      setCard(card);
    }

    safeSetBackground(getResources().getDrawable(R.drawable.com_appboy_card_background));
  }

  @Override
  protected int getLayoutResource() {
    return R.layout.com_appboy_app_store_review_card;
  }

  @Override
  public void onSetCard(final AppStoreReviewCard card) {
    mImage.setImageResource(mApplicationIconId);
    mTitle.setText(getResources().getString(R.string.com_appboy_app_store_review_card_title));
    mSubtitle.setText(getResources().getString(R.string.com_appboy_app_store_review_card_subtitle));
    mCardAction = new GooglePlayAppDetailsAction(mContext.getPackageName(), false, card.getAppStore());

    setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        card.setIsRead(true);
        if (mCardAction != null) {
          card.logClick();
          mCardAction.execute(mContext);
        }
      }
    });
  }
}
