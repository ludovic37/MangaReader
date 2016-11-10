package com.ludovic.crespeau.mangareader.interactor;

import com.ludovic.crespeau.mangareader.view.page.PageView;

/**
 * Created by crespeau on 10/11/2016.
 */

public interface PageInteractor {
    void chapter(PageView pageView, String mangaId, int chapterId);
}
