package ru.ftc.android.shifttemple.features.tasks.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.tasks.domain.model.Bid;

final class BidsAdapter extends RecyclerView.Adapter<BidsAdapter.BidHolder> {

    private final List<Bid> bids = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectBidListener selectBidListener;

    BidsAdapter(Context context, SelectBidListener selectBidListener) {
        inflater = LayoutInflater.from(context);
        this.selectBidListener = selectBidListener;
    }

    @NonNull
    @Override
    public BidHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.bid_item, parent, false);
        return new BidHolder(itemView, selectBidListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BidHolder holder, int position) {
        holder.bind(bids.get(position));
    }

    @Override
    public int getItemCount() {
        return bids.size();
    }

    public void setBids(List<Bid> bidsList) {
        bids.clear();
        bids.addAll(bidsList);
        notifyDataSetChanged();
    }

    class BidHolder extends RecyclerView.ViewHolder {

        private final TextView bidUserNameView;
        private final TextView bidTextView;
        private final TextView bidDateView;
        private final SelectBidListener selectBidListener;

        BidHolder(View view, SelectBidListener selectBidListener) {
            super(view);
            this.selectBidListener = selectBidListener;
            bidUserNameView = view.findViewById(R.id.bid_item_username);
            bidTextView = view.findViewById(R.id.bid_item_text);
            bidDateView = view.findViewById(R.id.bid_item_date);
        }

        void bind(final Bid bid) {
            bidUserNameView.setText(bid.getUserName());
            bidTextView.setText(bid.getText());
            bidDateView.setText(bid.getDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectBidListener.onBidSelect(bid);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectBidListener.onBidLongClick(bid);
                    return true;
                }
            });

        }

    }

    interface SelectBidListener {

        void onBidSelect(Bid bid);

        void onBidLongClick(Bid bid);

    }
}

