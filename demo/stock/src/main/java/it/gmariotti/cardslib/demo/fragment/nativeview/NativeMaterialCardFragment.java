/*
 * ******************************************************************************
 *   Copyright (c) 2013-2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */

package it.gmariotti.cardslib.demo.fragment.nativeview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import it.gmariotti.cardslib.demo.R;
import it.gmariotti.cardslib.demo.fragment.BaseMaterialFragment;
import it.gmariotti.cardslib.library.cards.HalfColoredCard;
import it.gmariotti.cardslib.library.cards.ProgressCard;
import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.IconSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.view.CardViewNative;

/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class NativeMaterialCardFragment extends BaseMaterialFragment {

    public static final int SIMULATED_REFRESH_LENGTH = 3000;
    ProgressCard progressCard;

    @Override
    protected int getSubTitleHeaderResourceId() {
        return 0;
    }

    @Override
    protected int getTitleHeaderResourceId() {
        return 0;
    }

    @Override
    protected String getDocUrl() {
        return null;
    }

    @Override
    protected String getSourceUrl() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.demo_fragment_native_materialcard, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCards();

    }

    private void initCards() {

        init_largeimage_text();
        init_largeimage();
        initProgressCard();
        init_HalfColoredCard();
    }


    /**
     * Builds a Material Card with Large Image and Text
     */
    private void init_largeimage_text() {

        //Create a Card, set the title over the image and set the thumbnail
        MaterialLargeImageCard card = new MaterialLargeImageCard(getActivity());
        card.setTextOverImage("Italian Beaches");
        card.setDrawableCardThumbnail(R.drawable.sea);

        //Set the title and subtitle in the card
        card.setTitle("This is my favorite local beach");
        card.setSubTitle("A wonderful place");

         // Set supplemental actions
        TextSupplementalAction t1 = new TextSupplementalAction(getActivity(), R.id.text1);
        t1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text SHARE ",Toast.LENGTH_SHORT).show();
            }
        });
        card.addSupplementalAction(t1);

        TextSupplementalAction t2 = new TextSupplementalAction(getActivity(), R.id.text2);
        t2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        card.addSupplementalAction(t2);

        //Set the layout for supplemental actions
        card.setLayout_supplemental_actions_id(R.layout.carddemo_native_material_supplemental_actions_large);

        //Very important call: build the card
        card.build();

        //Set card in the CardViewNative
        CardViewNative cardView = (CardViewNative) getActivity().findViewById(R.id.carddemo_largeimage_text);
        cardView.setCard(card);
    }

    /**
     * Builds a Material Card with Large and small icons as supplemental actions
     */
    private void init_largeimage() {

        //Create a Card, set the title over the image and set the thumbnail
        MaterialLargeImageCard card = new MaterialLargeImageCard(getActivity());
        card.setTextOverImage("Italian Beaches");
        card.setDrawableCardThumbnail(R.drawable.im_beach);

        // Set supplemental actions as icon
        IconSupplementalAction t1 = new IconSupplementalAction(getActivity(), R.id.ic1);
        t1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text SHARE ",Toast.LENGTH_SHORT).show();
            }
        });
        card.addSupplementalAction(t1);

        IconSupplementalAction t2 = new IconSupplementalAction(getActivity(), R.id.ic2);
        t2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        card.addSupplementalAction(t2);

        //Set the layout for supplemental actions
        card.setLayout_supplemental_actions_id(R.layout.carddemo_native_material_supplemental_actions_large_icon);

        //Very important call: build the card
        card.build();

        //Set card in the CardViewNative
        CardViewNative cardView = (CardViewNative) getActivity().findViewById(R.id.carddemo_largeimage);
        cardView.setCard(card);
    }

    private void initProgressCard(){


        progressCard = new ProgressCard(getActivity());
        progressCard.setUseProgressBar(true);

        CardHeader header = new CardHeader(getActivity());
        header.setTitle("Progress Card");
        header.setPopupMenu(R.menu.update,new CardHeader.OnClickCardHeaderPopupMenuListener() {
            @Override
            public void onMenuItemClick(BaseCard card, MenuItem item) {
                if (item.getItemId() == R.id.action_update){
                    new UpdateAsyncTask(4000).execute();
                    progressCard.updateProgressBar(false, false);
                    progressCard.notifyDataSetChanged();
                }

            }
        });
        progressCard.addCardHeader(header);

        //Set card in the CardViewNative
        final CardViewNative cardView = (CardViewNative) getActivity().findViewById(R.id.carddemo_progress);
        cardView.setCard(progressCard);


        /**
         * Simulate Refresh with 4 seconds sleep
         */
        new UpdateAsyncTask(SIMULATED_REFRESH_LENGTH).execute();
        progressCard.updateProgressBar(false, false);
    }

    /**
     * Builds a Material HalfColored Card
     */
    private void init_HalfColoredCard() {

        //Create a Card, set the title over the image and set the thumbnail
        HalfColoredCard card = new HalfColoredCard(getActivity()) {
            @Override
            protected void setupInnerViewElementsSecondHalf(View secondHalfView) {

                TextView mSimpleTitleView = (TextView) secondHalfView.findViewById(R.id.carddemo_halfcolored_simple_title);
                if (mSimpleTitleView!=null) {
                    mSimpleTitleView.setText("It is just an example!");
                }

            }
        };
        card.setTitleOverColor("22 mins to Ancona");
        card.setSubTitleOverColor("Light traffic on SS16");
        card.setColorResourceId(R.color.carddemo_halfcolored_color);
        card.setSecondHalfViewStubLayoutId(R.layout.carddemo_native_halfcolored_simple_title);

        //Set card in the CardViewNative
        CardViewNative cardView = (CardViewNative) getActivity().findViewById(R.id.carddemo_halfcolored);
        cardView.setCard(card);
    }


    /**
     * Only for test
     */
    class UpdateAsyncTask extends AsyncTask<Void, Void, Void>{

        int mRefresh_lenght = SIMULATED_REFRESH_LENGTH;

        UpdateAsyncTask(int refresh_lenght){
            mRefresh_lenght = refresh_lenght;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(mRefresh_lenght);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Notify PullToRefreshAttacher that the refresh has finished
            progressCard.setTitle("Updated card ");
            progressCard.updateProgressBar(true, true);
            progressCard.notifyDataSetChanged();

        }
    }

    /*
    //
        //Create a Card
        MaterialLargeImageCard cardx = new MaterialLargeImageCard(getActivity());
        cardx.setTextOverImage("Italian Beaches");
        cardx.addCardThumbnail(new MyMaterialCardThumbnail(getActivity()));

        cardx.setTitle("This is my favorite local beach");

        TextSupplementalAction t1 = new TextSupplementalAction(getActivity(), R.id.text1);
        t1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text SHARE ",Toast.LENGTH_SHORT).show();
            }
        });
        cardx.addSupplementalAction(t1);

        TextSupplementalAction t2 = new TextSupplementalAction(getActivity(), R.id.text2);
        t2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        cardx.addSupplementalAction(t2);

        cardx.setLayout_supplemental_actions_id(R.layout.carddemo_native_material_supplemental_actions_large);

        cardx.build();

        //Set card in the CardViewNative
        CardViewNative cardViewz = (CardViewNative) getActivity().findViewById(R.id.carddemo_largeimage);
        cardViewz.setCard(cardx);
    }

    public class MyMaterialCardThumbnail extends MaterialLargeImageCardThumbnail {

        public MyMaterialCardThumbnail(Context context) {
            super(context);
            setExternalUsage(true);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View imageView) {
            super.setupInnerViewElements(parent, imageView);

            Picasso.with(getContext())
                    .load("https://lh5.googleusercontent.com/-squZd7FxR8Q/UyN5UrsfkqI/AAAAAAAAbAo/VoDHSYAhC_E/s96/new%2520profile%2520%25282%2529.jpg")
                    .into((ImageView) imageView);

        }
    }
     */
}