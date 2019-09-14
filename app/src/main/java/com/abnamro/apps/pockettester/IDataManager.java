package com.abnamro.apps.pockettester;

import java.util.List;

public interface IDataManager {
    List<ItemTypeInfo> getTopics();
    int getNoteCount(ItemTypeInfo topic);
}
