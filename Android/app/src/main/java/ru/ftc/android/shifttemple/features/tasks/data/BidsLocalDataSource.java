package ru.ftc.android.shifttemple.features.tasks.data;

import java.util.Set;

public interface BidsLocalDataSource {

    Set<String> getBidIds();

    void putBidId(String id);

}

